package com.mobkata;

public class Account {
    private int profit;

    public Account(int balance) {
        profit = 0;
    }

    public void buy(int price) {
        profit -= price;
    }

    public int profit() {
        return profit;
    }
}
