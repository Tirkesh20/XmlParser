package by.epamtc.xmlparser.bean;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Bank implements Serializable,Comparable<Bank> {
    @Serial
    private static final long serialVersionUID = 5L;

    public String id;
    public String name;
    public String country;
    public DepositType deposit_type;
    public String depositor;
    public String accountId;
    public float amountOnDeposit;
    public float profitability;
    public LocalDateTime timeConstraints;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public DepositType getDeposit_type() {
        return deposit_type;
    }

    public void setDeposit_type(DepositType deposit_type) {
        this.deposit_type = deposit_type;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public float getAmountOnDeposit() {
        return amountOnDeposit;
    }

    public void setAmountOnDeposit(float amountOnDeposit) {
        this.amountOnDeposit = amountOnDeposit;
    }

    public float getProfitability() {
        return profitability;
    }

    public void setProfitability(float profitability) {
        this.profitability = profitability;
    }

    public LocalDateTime getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(LocalDateTime timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    @Override
    public int compareTo(Bank o) {
        return id.compareTo(o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Float.compare(bank.amountOnDeposit, amountOnDeposit) == 0 && Float.compare(bank.profitability, profitability) == 0 && Objects.equals(id, bank.id) && Objects.equals(name, bank.name) && Objects.equals(country, bank.country) && deposit_type == bank.deposit_type && Objects.equals(depositor, bank.depositor) && Objects.equals(accountId, bank.accountId) && Objects.equals(timeConstraints, bank.timeConstraints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, deposit_type, depositor, accountId, amountOnDeposit, profitability, timeConstraints);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", type=" + deposit_type +
                ", depositor='" + depositor + '\'' +
                ", accountId='" + accountId + '\'' +
                ", amountOnDeposit=" + amountOnDeposit +
                ", profitability=" + profitability +
                ", timeConstraints='" + timeConstraints + '\'' +
                '}';
    }

    public enum DepositType {
        CUMULATIVE("накопительный"),SETTLEMENT("рассчетный"),TERM("срочный"),
        ON_DEMAND("до востребования"),SAVING("сберегательный"),METALLIC("металический");

        private String type;

        DepositType(String type) {
            this.type=type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
