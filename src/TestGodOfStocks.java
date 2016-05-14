import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestGodOfStocks {

    @Test
    public void no_operation_needed_when_no_stock_price() {
        GodOfStocks godOfStocks = new GodOfStocks();

        List<StockOperation> operations = godOfStocks.operationsForMaxProfit(new ArrayList<Integer>());

        assertEquals(new ArrayList<StockOperation>(), operations);
    }
}
