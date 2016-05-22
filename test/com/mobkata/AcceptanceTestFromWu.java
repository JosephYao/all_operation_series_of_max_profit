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

    private void assertStockOperationEquals(List<List<StockOperation>> expected, List<Integer> prices) {
        List<List<StockOperation>> actual = godOfStocks.operationsForMaxProfit(prices);
        assertEquals(expected.size(), actual.size());
        expected.stream().forEach(
                series -> assertThat(actual, hasItem(series)));
    }
}
