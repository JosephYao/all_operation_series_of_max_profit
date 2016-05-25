package com.mobkata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAccountWithEnoughBalance {

    private static final int PRICE = 10;
    private static final int ENOUGH_BALANCE = 100;
    Account account = new Account(ENOUGH_BALANCE);

    @Test
    public void profit_when_buy_with_enough_balance() {
        account.buy(PRICE);

        assertEquals(-PRICE, account.profit());
    }

    @Test
    public void profit_when_after_sell() {
        account.buy(PRICE);

        account.sell(PRICE + 1);

        assertEquals(1, account.profit());
    }
}
