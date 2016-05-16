import java.util.ArrayList;
import java.util.List;

public class GodOfStocks {
    public List<StockOperation> operationsForMaxProfit(List<Integer> prices) {
        List<StockOperation> stockOperations = new ArrayList<StockOperation>();

        if (prices.size() == 1)
            stockOperations.add(StockOperation.PASS);

        return stockOperations;
    }
}
