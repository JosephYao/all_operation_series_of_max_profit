package com.mobkata;

import org.junit.Test;

import static com.mobkata.StockOperation.PASS;
import static com.mobkata.account.TestAccountHelper.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class AcceptanceTestWithNotEnoughBalance {

    GodOfStocks godOfStocks = new GodOfStocks(0);

    @Test
    public void can_only_pass_when_not_enough_balance_to_buy_stock() {
        assertStockOperationEquals(
                asList(asList(
                        PASS, PASS, PASS
                )),
                asList(LOWER_PRICE, HIGHER_PRICE, LOWER_PRICE), godOfStocks);

    }

    @Test
    public void max_profit_is_0_when_not_enough_balance_to_buy_stock() {
        assertEquals(0, godOfStocks.maxProfit(
                asList(LOWER_PRICE, HIGHER_PRICE, LOWER_PRICE)));
    }

}
