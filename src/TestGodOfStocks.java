import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestGodOfStocks {

    GodOfStocks godOfStocks = new GodOfStocks();

    @Test
    public void no_operation_needed_when_no_stock_price() {
        assertStockOperationEquals(Arrays.<StockOperation>asList(), Arrays.<Integer>asList());
    }

    @Test
    public void only_pass_operation_can_get_0_profit_when_only_one_stock_price() {
        assertStockOperationEquals(Arrays.asList(StockOperation.PASS), Arrays.asList(1));
    }

    private void assertStockOperationEquals(List<StockOperation> expected, List<Integer> prices) {
        assertEquals(expected, godOfStocks.operationsForMaxProfit(prices));
    }
}
