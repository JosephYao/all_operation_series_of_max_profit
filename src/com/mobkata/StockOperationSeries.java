package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.StockOperation.*;

public class StockOperationSeries {

    private List<Integer> prices;
    private List<StockOperation> operations;

    public StockOperationSeries(List<StockOperation> operations, List<Integer> prices) {
        this.operations = operations;
        this.prices = prices;
    }

    public Integer sum() {
        return operations.stream().
                mapToInt(this::profitOf).
                sum();
    }

    private int profitOf(StockOperation operation) {
        switch (operation) {
            case BUY:
                return -priceOf(operation);
            case SELL:
                return priceOf(operation);
            default:
                return 0;
        }
    }

    private Integer priceOf(StockOperation operation) {
        return prices.get(operations.indexOf(operation));
    }

    public List<StockOperation> operations() {
        return operations;
    }

    public StockOperationSeries createCompleteSeries() {
        if (operations.size() == prices.size())
            return this;

        if (lastOperation() == PASS)
            return new StockOperationSeries(new ArrayList<StockOperation>() {{
                addAll(operations);
                add(PASS);
            }}, prices).createCompleteSeries();
        else if (lastOperation() == BUY)
            return new StockOperationSeries(new ArrayList<StockOperation>() {{
                addAll(operations);
                add(SELL);
            }}, prices).createCompleteSeries();
        else
            return new StockOperationSeries(new ArrayList<StockOperation>() {{
                addAll(operations);
                add(COOL);
            }}, prices).createCompleteSeries();
    }

    private StockOperation lastOperation() {
        return operations.get(operations.size() - 1);
    }
}
