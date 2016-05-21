package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.StockOperation.PASS;
import static com.mobkata.StockOperation.SELL;

public class StockOperationSeries {

    private List<Integer> prices;
    private List<StockOperation> operations;

    public StockOperationSeries(List<StockOperation> operations, List<Integer> prices) {
        this.operations = operations;
        this.prices = prices;
    }

    public Integer sum() {
        int sum = 0;

        for (int i = 0; i < operations.size(); i++) {
            switch (operations.get(i)) {
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

    public List<StockOperation> operations() {
        return operations;
    }

    public StockOperationSeries createCompleteSeries() {
        if (operations.size() == prices.size())
            return this;

        if (lastOperationOf() == PASS)
            return new StockOperationSeries(new ArrayList<StockOperation>() {{
                addAll(operations);
                add(PASS);
            }}, prices);
        else
            return new StockOperationSeries(new ArrayList<StockOperation>() {{
                addAll(operations);
                add(SELL);
            }}, prices);
    }

    private StockOperation lastOperationOf() {
        return operations.get(operations.size() - 1);
    }
}
