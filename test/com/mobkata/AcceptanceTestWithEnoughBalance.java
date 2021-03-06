package com.mobkata;

import org.junit.Test;

import java.util.List;

import static com.mobkata.StockOperation.*;
import static com.mobkata.account.TestAccountHelper.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class AcceptanceTestWithEnoughBalance {

    GodOfStocks godOfStocks = new GodOfStocks(ENOUGH_BALANCE);

    @Test
    public void no_operation_needed_when_no_stock_price() {
        assertStockOperationEquals(
                asList(),
                asList(), godOfStocks);
    }

    @Test
    public void only_pass_operation_can_get_0_profit_when_only_one_stock_price() {
        assertStockOperationEquals(
                asList(
                        asList(PASS)),
                asList(LOWER_PRICE), godOfStocks);
    }

    @Test
    public void only_pass_operation_can_get_0_profit_when_first_price_higher_than_second_one() {
        assertStockOperationEquals(
                asList(
                        asList(PASS, PASS)),
                asList(HIGHER_PRICE, LOWER_PRICE), godOfStocks);
    }

    @Test
    public void buy_sell_and_pass_pass_both_can_get_0_profit_when_two_price_identical() {
        assertStockOperationEquals(
                asList(
                        asList(PASS, PASS)
                ),
                asList(LOWER_PRICE, LOWER_PRICE), godOfStocks
        );
    }

    @Test
    public void buy_sell_can_get_max_profit_when_first_price_lower_than_higher_price() {
        assertStockOperationEquals(
                asList(
                        asList(BUY, SELL, COOL)
                ),
                asList(LOWER_PRICE, HIGHER_PRICE, LOWER_PRICE), godOfStocks
        );
    }
    
    @Test
    public void should_cool_after_sell() {
        assertStockOperationEquals(
                asList(
                        asList(BUY, SELL, COOL)
                ),
                asList(LOWER_PRICE, HIGHER_PRICE, LOWER_PRICE), godOfStocks
        );
    }

    @Test
    public void can_buy_after_pass() {
        List<Integer> prices = asList(LOWER_PRICE + 1, LOWER_PRICE, HIGHER_PRICE, LOWER_PRICE);

        assertEquals(HIGHER_PRICE - LOWER_PRICE, godOfStocks.maxProfit(prices));
        assertStockOperationEquals(
                asList(
                        asList(PASS, BUY, SELL, COOL)
                ),
                prices, godOfStocks);
    }

    @Test
    public void can_pass_after_cool() {
        assertStockOperationEquals(
                asList(
                        asList(BUY, SELL, COOL, PASS)
                ),
                asList(LOWER_PRICE, HIGHER_PRICE, LOWER_PRICE, LOWER_PRICE), godOfStocks
        );
    }

    @Test
    public void can_buy_after_cool() {
        assertStockOperationEquals(
                asList(
                        asList(BUY, SELL, COOL, BUY, SELL, COOL)
                ),
                asList(LOWER_PRICE, HIGHER_PRICE, LOWER_PRICE, LOWER_PRICE, HIGHER_PRICE, LOWER_PRICE), godOfStocks
        );
    }

    @Test
    public void can_pass_after_buy() {
        List<Integer> prices = asList(LOWER_PRICE, LOWER_PRICE + 1, HIGHER_PRICE, LOWER_PRICE);

        assertEquals(HIGHER_PRICE - LOWER_PRICE, godOfStocks.maxProfit(prices));
        assertStockOperationEquals(
                asList(
                        asList(BUY, PASS, SELL, COOL)
                ),
                prices, godOfStocks);
    }

    @Test
    public void can_pass_twice_after_buy() {
        List<Integer> prices = asList(LOWER_PRICE, LOWER_PRICE + 1, LOWER_PRICE + 1, HIGHER_PRICE, LOWER_PRICE);

        assertEquals(HIGHER_PRICE - LOWER_PRICE, godOfStocks.maxProfit(prices));
        assertStockOperationEquals(
                asList(
                        asList(BUY, PASS, PASS, SELL, COOL)
                ),
                prices, godOfStocks);
    }

}
