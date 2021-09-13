package by.epamtc.xmlparser.parsers.sax;

public enum BanksXMLTag {
    BANKS("banks"),BANK("bank"),ID("id"),NAME("name"),COUNTRY("country"),
    DEPOSITOR("depositor"),DEPOSIT_TYPE("deposit_type"),
    PROFITABILITY("profitability"),AMOUNT_ON_DEPOSIT("amountOnDeposit"),TIME_CONSTRAINTS("timeConstraints"),
    ;

    String val;

    BanksXMLTag(String val) {
        this.val=val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
