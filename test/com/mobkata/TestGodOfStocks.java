package com.mobkata;

import org.junit.Test;

import java.util.List;

import static com.mobkata.StockOperation.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;

public class TestGodOfStocks {

    private static final int HIGHER_PRICE = 10;
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

    @Test
    public void buy_sell_can_get_max_profit_when_first_price_lower_than_higher_price() {
        assertStockOperationEquals(
                asList(
                        asList(BUY, SELL)
                ),
                asList(LOWER_PRICE, HIGHER_PRICE)
        );
    }
    
    @Test
    public void should_cool_after_sell() {
        assertStockOperationEquals(
                asList(
                        asList(BUY, SELL, COOL)
                ),
                asList(LOWER_PRICE, HIGHER_PRICE, LOWER_PRICE)
        );
    }

    @Test
    public void can_buy_after_pass() {
        List<Integer> prices = asList(LOWER_PRICE + 1, LOWER_PRICE, HIGHER_PRICE);
        assertEquals(HIGHER_PRICE - LOWER_PRICE, godOfStocks.maxProfit(prices));
        assertStockOperationEquals(asList(asList(PASS, BUY, SELL)),prices);
    }

    @Test
    public void can_pass_after_cool() {
        assertStockOperationEquals(
                asList(
                        asList(BUY, SELL, COOL, PASS)
                ),
                asList(LOWER_PRICE, HIGHER_PRICE, LOWER_PRICE, LOWER_PRICE)
        );
    }

    @Test
    public void can_buy_after_cool() {
        assertStockOperationEquals(
                asList(
                        asList(BUY, SELL, COOL, BUY, SELL)
                ),
                asList(LOWER_PRICE, HIGHER_PRICE, LOWER_PRICE, LOWER_PRICE, HIGHER_PRICE)
        );
    }

    @Test
    public void can_pass_after_buy() {
        List<Integer> prices = asList(LOWER_PRICE, LOWER_PRICE + 1, HIGHER_PRICE);
        assertEquals(HIGHER_PRICE - LOWER_PRICE, godOfStocks.maxProfit(prices));
        assertStockOperationEquals(asList(asList(BUY, PASS, SELL)),prices);
    }

    @Test
    public void can_pass_twice_after_buy() {
        List<Integer> prices = asList(LOWER_PRICE, LOWER_PRICE + 1, LOWER_PRICE + 1, HIGHER_PRICE);
        assertEquals(HIGHER_PRICE - LOWER_PRICE, godOfStocks.maxProfit(prices));
        assertStockOperationEquals(asList(asList(BUY, PASS, PASS, SELL)),prices);
    }

    private void assertStockOperationEquals(List<List<StockOperation>> expected, List<Integer> prices) {
        List<List<StockOperation>> actual = godOfStocks.operationsForMaxProfit(prices);
        assertEquals(expected, actual);
        expected.stream().forEach(
                series -> assertThat(actual, hasItem(series)));
    }
}
