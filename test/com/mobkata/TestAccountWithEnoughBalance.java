package com.mobkata;

import org.junit.Test;

import static com.mobkata.TestAccountHelper.*;
import static org.junit.Assert.assertEquals;

public class TestAccountWithEnoughBalance {

    Account account = new Account(ENOUGH_BALANCE);

    @Test
    public void profit_is_0_for_a_new_account() {
        assertEquals(0, account.profit());
    }

    @Test
    public void profit_when_buy_with_enough_balance() {
        account = account.buy(PRICE);

        assertEquals(-PRICE, account.profit());
    }

    @Test
    public void profit_when_after_sell() {
        account = account.buy(PRICE);

        account = account.sell(PRICE + 1);

        assertEquals(1, account.profit());
    }
}
