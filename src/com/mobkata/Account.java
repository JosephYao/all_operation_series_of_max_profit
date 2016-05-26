package com.mobkata;

public class Account {
    private int profit;

    public Account(int balance) {
        this(0, 0);
    }

    public Account(int balance, int profit) {
        this.profit = profit;
    }

    public Account buy(int price) {
        return new Account(0, profit - price);
    }

    public int profit() {
        return profit;
    }

    public Account sell(int price) {
        profit += price;
        return this;
    }
}
