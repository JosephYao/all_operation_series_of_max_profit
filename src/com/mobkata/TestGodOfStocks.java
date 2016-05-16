package com.mobkata;

import org.junit.Test;

import java.util.List;

import static com.mobkata.StockOperation.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class TestGodOfStocks {

    private static final int HIGHER_PRICE = 2;
    private static final int LOWER_PRICE = 1;
    GodOfStocks godOfStocks = new GodOfStocks();

    @Test
    public void no_operation_needed_when_no_stock_price() {
        assertStockOperationEquals(
                asList(),
                asList());
    }

    @Test
    public void only_pass_operation_can_get_0_profit_when_only_one_stock_price() {
        assertStockOperationEquals(
                asList(
                        asList(PASS)),
                asList(LOWER_PRICE));
    }

    @Test
    public void only_pass_operation_can_get_0_profit_when_first_price_higher_than_second_one() {
        assertStockOperationEquals(
                asList(
                        asList(PASS, PASS)),
                asList(HIGHER_PRICE, LOWER_PRICE));
    }

    @Test
    public void buy_sell_and_pass_pass_both_can_get_0_profit_when_two_price_identical() {
        assertStockOperationEquals(
                asList(
                        asList(PASS, PASS),
                        asList(BUY, SELL)
                ),
                asList(LOWER_PRICE, LOWER_PRICE)
        );
    }

    private void assertStockOperationEquals(List<List<StockOperation>> expected, List<Integer> prices) {
        assertEquals(expected, godOfStocks.operationsForMaxProfit(prices));
    }
}
