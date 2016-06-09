package com.mobkata;

import org.junit.Test;

import java.util.List;

import static com.mobkata.StockOperation.PASS;
import static com.mobkata.account.TestAccountHelper.HIGHER_PRICE;
import static com.mobkata.account.TestAccountHelper.LOWER_PRICE;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AcceptanceTestWithNotEnoughBalance {

    GodOfStocks godOfStocks = new GodOfStocks(0);

    @Test
    public void can_only_pass_when_not_enough_balance_to_buy_stock() {
        assertStockOperationEquals(
                asList(asList(
                        PASS, PASS, PASS
                )),
                asList(LOWER_PRICE, HIGHER_PRICE, LOWER_PRICE));

    }

    private void assertStockOperationEquals(List<List<StockOperation>> expected, List<Integer> prices) {
        List<List<StockOperation>> actual = godOfStocks.operationsForMaxProfit(prices);
        assertEquals(expected, actual);
        assertEquals(expected.size(), actual.size());
        expected.stream().forEach(
                series -> assertThat(actual, hasItem(series)));
    }
}
