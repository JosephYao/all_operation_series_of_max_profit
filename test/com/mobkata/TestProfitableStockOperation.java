package com.mobkata;

import org.junit.Test;

import static com.mobkata.ProfitableStockOperation.profitableStockOperation;
import static com.mobkata.StockOperation.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class TestProfitableStockOperation {

    @Test
    public void price_is_different_for_two_buy_operations() {
        assertProfitEquals(-4, profitableStockOperation(
                BUY,
                asList(BUY, SELL, COOL),
                asList(1, 2, 3, 4)));
    }

    private void assertProfitEquals(int expected, ProfitableStockOperation profitableStockOperation) {
        assertEquals(expected, profitableStockOperation.profit());
    }
}
