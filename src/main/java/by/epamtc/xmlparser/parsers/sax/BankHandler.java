package by.epamtc.xmlparser.parsers.sax;

import by.epamtc.xmlparser.bean.Bank;
import by.epamtc.xmlparser.exceptions.ValidationException;
import jdk.internal.org.xml.sax.Attributes;
import jdk.internal.org.xml.sax.SAXException;
import jdk.internal.org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.ContentHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.EnumSet;
import java.util.Set;
import java.util.TreeSet;

public class BankHandler extends DefaultHandler  {
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
        BanksXMLTag banksXMLTag = BanksXMLTag.valueOf(localName.toUpperCase());
        if (ELEMENT_BANK.equals(qName)) {
            currentBank = new Bank();
            readAttributes(currentBank, attributes);
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

    @Override
    public void endElement(String uri,String localName,String qName){
        if ("bank".equals(localName)){
            banks.add(currentBank);
        }
    }

    public void characters(char[] ch,int start,int length) throws SAXException {
        String s=new String(ch,start,length).trim();
        if (currentBank!=null){
            switch (currentTag){
                case NAME -> currentBank.setName(s);
                case TIME_CONSTRAINTS -> currentBank.setTimeConstraints(LocalDateTime.parse(s));
                case AMOUNT_ON_DEPOSIT -> {
                    float amount=Float.parseFloat(s);
                    if (amount<0){
                        try {
                            throw new ValidationException("deposit can t be null");
                        } catch (ValidationException e) {
                            throw new SAXException(e.getMessage());
                        }
                    }
                }
                case PROFITABILITY -> {
                    try {
                    float profit=Float.parseFloat(s);
                    if (profit<0) {
                        throw new ValidationException("profit cant be 0");

                    }
                }catch (NumberFormatException|ValidationException e) {
                        throw new SAXException(e.getMessage());
                    }
                }
                case DEPOSITOR -> currentBank.setDepositor(s);
                case DEPOSIT_TYPE -> currentBank.setDeposit_type(Bank.DepositType.valueOf(s));
                default -> throw new SAXException("unknown attribute");
                }
          }
            currentTag=null;
        }
    }
