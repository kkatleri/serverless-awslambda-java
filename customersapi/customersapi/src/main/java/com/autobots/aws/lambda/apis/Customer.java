package com.autobots.aws.lambda.apis;

public class Customer {

    int id;
    String customerName;
    int rewardPoints;

    public Customer() {
    }

    public Customer(int id, String customerName, int rewardPoints) {
        this.id = id;
        this.customerName = customerName;
        this.rewardPoints = rewardPoints;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }
}
