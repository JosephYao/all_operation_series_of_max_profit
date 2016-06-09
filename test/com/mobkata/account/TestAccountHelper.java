package com.mobkata.account;

import com.mobkata.GodOfStocks;
import com.mobkata.StockOperation;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TestAccountHelper extends Account {
    public static final int ENOUGH_BALANCE = 100;
    public static final int PRICE = 20;
    public static final int HIGHER_PRICE = 10;
    public static final int LOWER_PRICE = 1;

    public TestAccountHelper(int balance) {
        super(balance);
    }

    public static void assertStockOperationEquals(List<List<StockOperation>> expected, List<Integer> prices, GodOfStocks godOfStocks) {
        List<List<StockOperation>> actual = godOfStocks.operationsForMaxProfit(prices);
        assertEquals(expected, actual);
        assertEquals(expected.size(), actual.size());
        expected.stream().forEach(
                series -> assertThat(actual, hasItem(series)));
    }

    public Account buyWithoutConsumer(int price) {
        return buy(price, account ->{});
    }
}
