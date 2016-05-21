package com.mobkata;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class GodOfStocks {
    public List<List<StockOperation>> operationsForMaxProfit(List<Integer> prices) {
        List<StockOperationSeries> allStockOperationsSeries = new StockOperationSeries(emptyList(), prices).towardsCompleteSeries();

        int maxProfit = allStockOperationsSeries.stream().
                mapToInt(series -> series.sum()).max().orElse(0);

        return allStockOperationsSeries.stream().
                filter(series -> series.sum() == maxProfit).
                map(series -> series.operations()).
                collect(toList());
    }

}
