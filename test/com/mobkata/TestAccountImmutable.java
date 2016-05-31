package com.mobkata;

import org.junit.Test;

import static com.mobkata.TestAccountHelper.*;
import static org.junit.Assert.assertEquals;

public class TestAccountImmutable {

    Account initial =  new Account(ENOUGH_BALANCE);
    private Account afterBuy;

    @Test
    public void account_is_immutable_after_buy() {
        afterBuyWith(PRICE);

        assertEquals(0, initial.profit());
        assertEquals(-PRICE, afterBuy.profit());
    }

    @Test
    public void account_is_immutable_after_sell() {
        afterBuyWith(PRICE);

        afterBuy.sell(PRICE + 1);

        assertEquals(0, initial.profit());
        assertEquals(-PRICE, afterBuy.profit());
    }

    @Test
    public void account_is_immutable_after_pass() {
        afterBuyWith(PRICE);

        Account afterSell = afterBuy.sell(PRICE + 1);
        Account afterPass = afterSell.pass();

        assertEquals(-PRICE, afterSell.profit());
        assertEquals(1, afterPass.profit());
    }

    private void afterBuyWith(int price) {
        afterBuy = initial.buy(price);
    }

}
