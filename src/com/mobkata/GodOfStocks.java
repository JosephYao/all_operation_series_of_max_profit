package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.StockOperation.*;

public class GodOfStocks {
    public List<List<StockOperation>> operationsForMaxProfit(List<Integer> prices) {
        final List<List<StockOperation>> allStockOperationsSeries = new ArrayList<>();

        if (prices.size() == 0)
            return allStockOperationsSeries;

        if (prices.size() == 1) {
            allStockOperationsSeries.add(new ArrayList<StockOperation>(){{
                add(PASS);
            }});
        }

        if (prices.size() == 2 && prices.get(0) > prices.get(1))
            allStockOperationsSeries.add(createStockOperationsSeries(
                    prices.size() - 1, new ArrayList<StockOperation>() {{
                        add(PASS);
                    }}
            ));

        if (prices.size() == 2 && prices.get(0) == prices.get(1)) {
            allStockOperationsSeries.add(createStockOperationsSeries(
                    prices.size() - 1, new ArrayList<StockOperation>() {{
                        add(BUY);
                    }}
            ));
            allStockOperationsSeries.add(createStockOperationsSeries(
                    prices.size() - 1, new ArrayList<StockOperation>() {{
                        add(PASS);
                    }}
            ));
        }

        if (prices.size() == 2 && prices.get(0) < prices.get(1))
        allStockOperationsSeries.add(createStockOperationsSeries(
                prices.size() - 1, new ArrayList<StockOperation>() {{
                    add(BUY);
                }}
        ));

        return allStockOperationsSeries;
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
