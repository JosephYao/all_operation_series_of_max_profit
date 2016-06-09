package com.mobkata;

import org.junit.Test;

import java.util.List;

import static com.mobkata.StockOperation.*;
import static com.mobkata.account.TestAccountHelper.ENOUGH_BALANCE;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AcceptanceTestFromSun {

    GodOfStocks godOfStocks = new GodOfStocks(ENOUGH_BALANCE);

    @Test
    public void two_transactions() {
        List<Integer> prices = asList(1, 4, 3, 2, 5, 1);

        assertEquals(6, godOfStocks.maxProfit(prices));
        assertStockOperationEquals(
                asList(
                        asList(BUY, SELL, COOL, BUY, SELL, COOL)
                ),
                prices);
    }

    @Test
    public void join_two_raises() {
        List<Integer> prices = asList(1, 3, 2, 4, 5, 2);
        assertEquals(4, godOfStocks.maxProfit(prices));
        assertStockOperationEquals(asList(asList(BUY, PASS, PASS, PASS, SELL, COOL)), prices);
    }

    @Test
    public void select_1_from_2() {
        List<Integer> prices = asList(1,4,2,3,2,1);
        assertEquals(3, godOfStocks.maxProfit(prices));
        assertStockOperationEquals(asList(asList(BUY, SELL, COOL, PASS, PASS, PASS)), prices);
    }
    
    @Test
    public void sell_ealier_for_next_raise() {
        List<Integer> prices = asList(1,3,4,2,5,2);
        assertEquals(5, godOfStocks.maxProfit(prices));
        assertStockOperationEquals(asList(asList(BUY, SELL, COOL, BUY, SELL, COOL)), prices);
    }

    private void assertStockOperationEquals(List<List<StockOperation>> expected, List<Integer> prices) {
        List<List<StockOperation>> actual = godOfStocks.operationsForMaxProfit(prices);
        assertEquals(expected.size(), actual.size());
        expected.stream().forEach(
                series -> assertThat(actual, hasItem(series)));
    }
}
