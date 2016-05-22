package com.mobkata;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class GodOfStocks {
    public List<List<StockOperation>> operationsForMaxProfit(List<Integer> prices) {
        List<StockOperationSeries> allStockOperationSeries = allStockOperationSeries(prices);

        int maxProfit = allStockOperationSeries.stream().
                mapToInt(StockOperationSeries::sum).max().orElse(0);

        return allStockOperationSeries.stream().
                filter(series -> series.sum() == maxProfit).
                map(StockOperationSeries::operations).
                collect(toList());
    }

    private List<StockOperationSeries> allStockOperationSeries(List<Integer> prices) {
        return new StockOperationSeries(emptyList(), prices, 0, false).towardsCompleteSeries();
    }

    public int maxProfit(List<Integer> prices) {
        return allStockOperationSeries(prices).stream().
                mapToInt(StockOperationSeries::sum).max().orElse(0);
    }
}
