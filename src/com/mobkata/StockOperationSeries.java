package com.mobkata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mobkata.StockOperation.*;
import static java.util.Collections.emptyList;

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

    public List<StockOperationSeries> createCompleteSeries() {
        if (prices.isEmpty())
            return emptyList();

        if (isSeriesComplete())
            return Arrays.asList(this);

        if (operations.size() == 0)
            return new ArrayList<StockOperationSeries>() {{
                addAll(towardsCompleteSeries(PASS));
                addAll(towardsCompleteSeries(BUY));
            }};

        if (lastOperation() == PASS)
            return towardsCompleteSeries(PASS);
        else if (lastOperation() == BUY)
            return towardsCompleteSeries(SELL);
        else
            return towardsCompleteSeries(COOL);
    }

    private boolean isSeriesComplete() {
        return operations.size() == prices.size();
    }

    private List<StockOperationSeries> towardsCompleteSeries(final StockOperation nextOperation) {
        return new StockOperationSeries(new ArrayList<StockOperation>() {{
            addAll(operations);
            add(nextOperation);
        }}, prices).createCompleteSeries();
    }

    private StockOperation lastOperation() {
        return operations.get(operations.size() - 1);
    }
}
