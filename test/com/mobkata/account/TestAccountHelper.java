package com.mobkata.account;

public class TestAccountHelper extends Account {
    public static final int ENOUGH_BALANCE = 100;
    public static final int PRICE = 20;
    public static final int HIGHER_PRICE = 10;
    public static final int LOWER_PRICE = 1;

    public TestAccountHelper(int balance) {
        super(balance);
    }

    public Account buyWithoutConsumer(int price) {
        return buy(price, account ->{});
    }
}
