package com.mobkata.account;

import org.junit.Test;

import static com.mobkata.account.TestAccountHelper.ENOUGH_BALANCE;
import static org.junit.Assert.assertEquals;

public class TestAccountSell {

    @Test
    public void balance_after_sell() {
        TestAccountHelper account = new TestAccountHelper(ENOUGH_BALANCE);

        Account accountAfterSell = account
                .buyWithoutConsumer(TestAccountHelper.PRICE)
                .sell(TestAccountHelper.PRICE + 1)
                .pass();

        assertEquals(ENOUGH_BALANCE + 1, accountAfterSell.balance());
    }
}
