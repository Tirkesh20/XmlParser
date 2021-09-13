package by.epamtc.xmlparser.parsers;

import by.epamtc.xmlparser.bean.Bank;
import by.epamtc.xmlparser.exceptions.ParseException;

import java.io.InputStream;
import java.util.Set;
import java.util.TreeSet;

public abstract class AbstractBanksBuilder {
    protected static final String NULL_EXCEPTION="File can t be null";

    protected Set<Bank> banks;

    public AbstractBanksBuilder(){
        banks=new TreeSet<>();
    }

    public Set<Bank> getBanks(){
        return banks;
    }

    public abstract void buildBankSet(InputStream content)throws ParseException;
}
