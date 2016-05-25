package com.mobkata;

public class Account {
    private int profit;

    public Account(int balance) {
        profit = 0;
    }

    public Account buy(int price) {
        profit -= price;
        return this;
    }

    public int profit() {
        return profit;
    }

    public Account sell(int price) {
        profit += price;
        return this;
    }
}
