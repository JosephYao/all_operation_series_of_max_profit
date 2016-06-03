package com.mobkata;

import java.util.function.Consumer;

public class Account {
    private int profit;
    private final int soldPrice;
    private int balance;

    public Account(int balance) {
        this(balance, 0, 0);
    }

    public Account(int balance, int profit, int soldPrice) {
        this.balance = balance;
        this.profit = profit;
        this.soldPrice = soldPrice;
    }

    public Account buy(int price, Consumer<Account> consumer) {
        if (price <= balance) {
            Account account = new Account(balance, profit - price, 0);
            consumer.accept(account);
            return account;
        }
        return new Account(balance, profit, soldPrice);
    }

    public int profit() {
        return profit;
    }

    public Account sell(int price) {
        return new Account(balance, profit, price);
    }

    public Account pass() {
        return new Account(balance, profit + soldPrice, 0);
    }

    public Account buy(int price) {
        return buy(price, account -> {});
    }
}
