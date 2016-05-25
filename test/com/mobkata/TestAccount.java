package com.mobkata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAccount {

    private static final int PRICE = 10;
    private static final int ENOUGH_BALANCE = 100;

    @Test
    public void profit_when_buy_with_enough_balance() {
        Account account = new Account(ENOUGH_BALANCE);

        account.buy(PRICE);

        assertEquals(-PRICE, account.profit());
    }
}
