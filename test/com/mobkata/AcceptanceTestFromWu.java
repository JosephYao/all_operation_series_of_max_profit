package com.mobkata;

import org.junit.Test;

import java.util.List;

import static com.mobkata.StockOperation.*;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AcceptanceTestFromWu {

    GodOfStocks godOfStocks = new GodOfStocks();

    @Test
    public void continuously_raise() {
        List<Integer> prices = asList(1,2,3,4,5);
        assertEquals(4, godOfStocks.maxProfit(prices));
        assertStockOperationEquals(asList(asList(BUY, PASS, PASS, PASS, SELL)), prices);
    }
    
    @Test
    public void continuously_drop() {
        List<Integer> prices = asList(5,4,3,2,1);
        assertEquals(0, godOfStocks.maxProfit(prices));
        assertStockOperationEquals(asList(asList(PASS, PASS, PASS, PASS, PASS)), prices);
    }
    
    @Test
    public void drop_rise_around_rise() {
        List<Integer> prices = asList(5,2,4,6,3,7);
        assertEquals(6, godOfStocks.maxProfit(prices));
        assertStockOperationEquals(asList(asList(PASS, BUY, SELL, COOL, BUY, SELL)), prices);
    }
    
    @Test
    public void multiple_plans() {
        List<Integer> prices = asList(1,2,2);
        assertEquals(1, godOfStocks.maxProfit(prices));
        assertStockOperationEquals(asList(
                asList(BUY, PASS, SELL),
                asList(BUY, SELL, COOL)), prices);
    }

    @Test
    public void multiple_before_one_rise() {
        List<Integer> prices = asList(5,5,5,5,3,4);
        assertEquals(1, godOfStocks.maxProfit(prices));
        assertStockOperationEquals(asList(
                asList(PASS, PASS, PASS, PASS, BUY, SELL),
                asList(PASS, BUY, SELL, COOL, BUY, SELL),
                asList(BUY, PASS, SELL, COOL, BUY, SELL),
                asList(BUY, SELL, COOL, PASS, BUY, SELL)), prices);
    }
    
    @Test
    public void long_but_easy_to_determine() {
        List<Integer> prices = asList(1,2,3,4,5,6,7,8,9,10,9,8,7,6,5,4,3,2,1, 2,3,4,5,6,7,8,9,10);
        assertEquals(18, godOfStocks.maxProfit(prices));
        assertStockOperationEquals(asList(
                asList(BUY, PASS, PASS, PASS, PASS, PASS, PASS, PASS, PASS, SELL, COOL, PASS, PASS, PASS, PASS, PASS, PASS, PASS, BUY, PASS, PASS, PASS, PASS, PASS, PASS, PASS, PASS, SELL)), prices);
    }

    private void assertStockOperationEquals(List<List<StockOperation>> expected, List<Integer> prices) {
        List<List<StockOperation>> actual = godOfStocks.operationsForMaxProfit(prices);
        assertEquals(expected.size(), actual.size());
        expected.stream().forEach(
                series -> assertThat(actual, hasItem(series)));
    }
}
