package com.mobkata;

import org.junit.Test;

import static com.mobkata.ProfitableStockOperation.profitableStockOperation;
import static com.mobkata.StockOperation.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class TestProfitableStockOperation {

    private static final int BUY_PRICE = 4;
    private static final int FIRST_BUY_PRICE = 1;
    private static final int ANY_PRICE = 2;

    @Test
    public void second_buy_price_is_different_from_first_buy() {
        assertProfitEquals(-BUY_PRICE, profitableStockOperation(
                BUY,
                asList(BUY, SELL, COOL),
                asList(FIRST_BUY_PRICE, ANY_PRICE, ANY_PRICE, BUY_PRICE)));
    }

    private void assertProfitEquals(int expected, ProfitableStockOperation profitableStockOperation) {
        assertEquals(expected, profitableStockOperation.profit());
    }
}
