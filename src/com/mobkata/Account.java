package com.mobkata;

import java.util.function.Consumer;

public class Account {
    private int profit;
    private final int soldPrice;

    public Account(int balance) {
        this(0, 0, 0);
    }

    public Account(int balance, int profit, int soldPrice) {
        this.profit = profit;
        this.soldPrice = soldPrice;
    }

    public Account buy(int price, Consumer<Account> consumer) {
        Account account = new Account(0, profit - price, 0);
        consumer.accept(account);
        return account;
    }

    public int profit() {
        return profit;
    }

    public Account sell(int price) {
        return new Account(0, profit, price);
    }

    public Account pass() {
        return new Account(0, profit + soldPrice, 0);
    }

    public Account buy(int price) {
        return buy(price, account -> {});
    }
}
