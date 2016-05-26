package com.mobkata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAccountImmutable {

    @Test
    public void account_is_immutable_after_buy() {
        Account initial = new Account(100);

        Account afterBuy = initial.buy(10);

        assertEquals(0, initial.profit());
        assertEquals(-10, afterBuy.profit());
    }
}
