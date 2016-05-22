package com.mobkata;

import org.junit.Test;

import java.util.List;

import static com.mobkata.StockOperation.*;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AcceptanceTestFromSun {

    GodOfStocks godOfStocks = new GodOfStocks();

    @Test
    public void two_transactions() {
        List<Integer> prices = asList(1, 4, 3, 2, 5);
        assertEquals(6, godOfStocks.maxProfit(prices));
        assertStockOperationEquals(asList(asList(BUY, SELL, COOL, BUY, SELL)), prices);
    }

    @Test
    public void join_two_raises() {
        List<Integer> prices = asList(1, 3, 2, 4, 5, 2);
        assertEquals(4, godOfStocks.maxProfit(prices));
        assertStockOperationEquals(asList(asList(BUY, PASS, PASS, PASS, SELL, COOL)), prices);
    }

    private void assertStockOperationEquals(List<List<StockOperation>> expected, List<Integer> prices) {
        List<List<StockOperation>> actual = godOfStocks.operationsForMaxProfit(prices);
        assertEquals(expected.size(), actual.size());
        expected.stream().forEach(
                series -> assertThat(actual, hasItem(series)));
    }
}
