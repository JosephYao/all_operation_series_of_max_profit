package com.mobkata;

import org.junit.Test;

import static com.mobkata.TestAccountHelper.*;
import static org.junit.Assert.assertEquals;

public class TestAccountProfitAfterSold {

    Account account = new Account(ENOUGH_BALANCE);

    @Test
    public void profit_not_changed_right_after_sold() {
        assertEquals(-PRICE, sellWithProfit(1).profit());
    }

    @Test
    public void profit_changed_on_next_day_after_sold_day() {
        assertEquals(1, sellWithProfit(1).pass().profit());
    }

    @Test
    public void profit_not_changed_if_pass_after_buy() {
        assertEquals(-PRICE, account.buy(PRICE).pass().profit());
    }

    @Test
    public void profit_not_changed_if_pass_after_pass() {
        assertEquals(0, account.pass().pass().profit());
    }

    private Account sellWithProfit(int profit) {
        return account.buy(PRICE).sell(PRICE + profit);
    }

}
