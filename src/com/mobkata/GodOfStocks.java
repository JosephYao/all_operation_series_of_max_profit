package com.mobkata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mobkata.StockOperation.*;
import static java.util.stream.Collectors.toList;

public class GodOfStocks {
    public List<List<StockOperation>> operationsForMaxProfit(List<Integer> prices) {
        List<StockOperationSeries> allStockOperationsSeries = allStockOperationSeries(prices);

        int maxProfit = allStockOperationsSeries.stream().
                mapToInt(series -> series.sum()).max().orElse(0);

        return allStockOperationsSeries.stream().
                filter(series -> series.sum() == maxProfit).
                map(series -> series.operations()).
                collect(toList());
    }

    private List<StockOperationSeries> allStockOperationSeries(List<Integer> prices) {
        final List<StockOperationSeries> allStockOperationsSeries = new ArrayList<>();

        if (prices.size() == 0)
            return allStockOperationsSeries;

        allStockOperationsSeries.add(
                new StockOperationSeries(Arrays.asList(PASS), prices).createCompleteSeries());
        allStockOperationsSeries.add(
                new StockOperationSeries(Arrays.asList(BUY), prices).createCompleteSeries());

        return allStockOperationsSeries;
    }

}
