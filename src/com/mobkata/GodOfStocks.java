package com.mobkata;

import java.util.List;

import static com.mobkata.StockOperationSeries.allStockOperationSeries;
import static java.util.stream.Collectors.toList;

public class GodOfStocks {
    public GodOfStocks(int balance) {

    }

    public List<List<StockOperation>> operationsForMaxProfit(List<Integer> prices) {
        List<StockOperationSeries> allStockOperationSeries = allStockOperationSeries(prices);

        int maxProfit = allStockOperationSeries.stream().
                mapToInt(StockOperationSeries::sum).max().orElse(0);

        return allStockOperationSeries.stream().
                filter(series -> series.sum() == maxProfit).
                map(StockOperationSeries::operations).
                collect(toList());
    }

    public int maxProfit(List<Integer> prices) {
        return allStockOperationSeries(prices).stream().
                mapToInt(StockOperationSeries::sum).max().orElse(0);
    }
}
