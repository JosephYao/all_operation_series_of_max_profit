package com.mobkata.account;

import org.junit.Test;

import static com.mobkata.account.TestAccountHelper.*;
import static org.junit.Assert.assertEquals;

public class TestAccountImmutable {

    TestAccountHelper initial =  new TestAccountHelper(ENOUGH_BALANCE);
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
        afterBuy = initial.buyWithoutConsumer(price);
    }

}
