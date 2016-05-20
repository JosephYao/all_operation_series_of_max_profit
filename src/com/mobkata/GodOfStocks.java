package com.mobkata;

import java.util.ArrayList;
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
                createStockOperationsSeries(prices.size() - 1, new ArrayList<StockOperation>(){{
                    add(PASS);
                }}, prices));
        allStockOperationsSeries.add(
                createStockOperationsSeries(prices.size() - 1, new ArrayList<StockOperation>(){{
                    add(BUY);
                }}, prices));

        return allStockOperationsSeries;
    }

    private StockOperationSeries createStockOperationsSeries(int numberOfPrices, List<StockOperation> currentSeries, List<Integer> prices) {
        if (numberOfPrices == 0)
            return new StockOperationSeries(currentSeries, prices);

        if (lastOperationOf(currentSeries) == PASS)
            currentSeries.add(PASS);
        else
            currentSeries.add(SELL);

        return new StockOperationSeries(currentSeries, prices);
    }

    private StockOperation lastOperationOf(List<StockOperation> currentSeries) {
        return currentSeries.get(currentSeries.size() - 1);
    }
}
