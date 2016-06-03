package com.mobkata.account;

public class TestAccountHelper extends Account {
    public static final int ENOUGH_BALANCE = 100;
    public static final int PRICE = 10;

    public TestAccountHelper(int balance) {
        super(balance);
    }

    public Account buyWithoutConsumer(int price) {
        return buy(price, account ->{});
    }
}
