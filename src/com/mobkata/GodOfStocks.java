package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.StockOperation.*;
import static java.util.Arrays.asList;

public class GodOfStocks {
    public List<List<StockOperation>> operationsForMaxProfit(List<Integer> prices) {
        final List<List<StockOperation>> allStockOperationsSeries = new ArrayList<>();

        if (prices.size() == 1)
            allStockOperationsSeries.add(asList(PASS));

        if (prices.size() == 2) {
            allStockOperationsSeries.add(asList(PASS, PASS));
            if (prices.get(0) == prices.get(1))
                allStockOperationsSeries.add(asList(BUY, SELL));
        }

        return allStockOperationsSeries;
    }
}
