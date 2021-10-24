package com.autobots.aws.lambda;

public class Payment {

    String name;
    String amount;
    String CCN;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCCN() {
        return CCN;
    }

    public void setCCN(String CCN) {
        this.CCN = CCN;
    }
}
