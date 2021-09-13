package by.epamtc.xmlparser.parsers.sax;

import by.epamtc.xmlparser.bean.Bank;
import jdk.internal.org.xml.sax.Attributes;
import jdk.internal.org.xml.sax.SAXException;
import jdk.internal.org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.Set;
import java.util.TreeSet;

public class BankHandler extends DefaultHandler {
    private Set<Bank> banks;
    private Bank currentBank;
    private BanksXMLTag currentTag;
    private EnumSet<BanksXMLTag> withText;
    private static final String ELEMENT_BANK="bank";
    public BankHandler() {
        banks =new TreeSet<>();
        withText =EnumSet.range(BanksXMLTag.COUNTRY,BanksXMLTag.TIME_CONSTRAINTS);
    }
    public Set<Bank> getBanks(){
        return banks;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    BanksXMLTag banksXMLTag=BanksXMLTag.valueOf(localName.toUpperCase());
    if (ELEMENT_BANK.equals(qName)){
        currentBank=new Bank();
        readAttributes(currentBank,attributes);
    }

    }

    private void readAttributes(Bank bank,Attributes attributes){
        for (int i=0;i<attributes.getLength();i++){
            BanksXMLTag attribute = BanksXMLTag.valueOf(attributes.getLocalName(i).toUpperCase());
        switch (attribute){
            case ID->bank.setId(attributes.getValue(i));
            case COUNTRY ->bank.setDepositor(attributes.getValue(i));
            case DEPOSIT_TYPE -> bank.setDeposit_type(Bank.DepositType.valueOf(attributes.getValue(i)));
        }
        }
    }
}
