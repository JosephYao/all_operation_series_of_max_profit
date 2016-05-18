package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.StockOperation.*;
import static java.util.stream.Collectors.toList;

public class GodOfStocks {
    public List<List<StockOperation>> operationsForMaxProfit(List<Integer> prices) {
        List<List<StockOperation>> allStockOperationsSeries = allStockOperationSeries(prices.size());

        int maxProfit = allStockOperationsSeries.stream().
                mapToInt(series -> sumOf(series, prices)).max().orElse(0);

        return allStockOperationsSeries.stream().
                filter(series -> sumOf(series, prices) == maxProfit).
                collect(toList());
    }

    private List<List<StockOperation>> allStockOperationSeries(int numberOfStockPrices) {
        final List<List<StockOperation>> allStockOperationsSeries = new ArrayList<>();

        if (numberOfStockPrices == 0)
            return allStockOperationsSeries;

        allStockOperationsSeries.add(
                createStockOperationsSeries(numberOfStockPrices - 1, new ArrayList<StockOperation>(){{
                    add(PASS);
                }}));
        allStockOperationsSeries.add(
                createStockOperationsSeries(numberOfStockPrices - 1, new ArrayList<StockOperation>(){{
                    add(BUY);
                }}));

        return allStockOperationsSeries;
    }

    private Integer sumOf(List<StockOperation> series, List<Integer> prices) {
        int sum = 0;

        for (int i = 0; i < prices.size(); i++) {
            switch (series.get(i)) {
                case PASS:
                    break;
                case BUY:
                    sum -= prices.get(i);
                    break;
                case SELL:
                    sum += prices.get(i);
                    break;
            }
        }

        return sum;
    }

    private List<StockOperation> createStockOperationsSeries(int numberOfPrices, List<StockOperation> currentSeries) {
        if (numberOfPrices == 0)
            return currentSeries;

        if (lastOperationOf(currentSeries) == PASS)
            currentSeries.add(PASS);
        else
            currentSeries.add(SELL);

        return currentSeries;
    }

    private StockOperation lastOperationOf(List<StockOperation> currentSeries) {
        return currentSeries.get(currentSeries.size() - 1);
    }
}
