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
        if (hasNoPrice())
            return emptyList();

        if (isSeriesComplete())
            return Arrays.asList(this);

        if (hasNoOperation() || lastOperation() == PASS)
            return towardsCompleteSeriesWith(PASS, BUY);
        else if (lastOperation() == BUY)
            return towardsCompleteSeriesWithOnePossibility(SELL);
        else
            return towardsCompleteSeriesWithOnePossibility(COOL);
    }

    private boolean hasNoPrice() {
        return prices.isEmpty();
    }

    private boolean hasNoOperation() {
        return operations.size() == 0;
    }

    private ArrayList<StockOperationSeries> towardsCompleteSeriesWith(final StockOperation... nextOperations) {
        return new ArrayList<StockOperationSeries>() {{
            for (StockOperation nextOperation : nextOperations)
                addAll(towardsCompleteSeriesWithOnePossibility(nextOperation));
        }};
    }

    private boolean isSeriesComplete() {
        return operations.size() == prices.size();
    }

    private List<StockOperationSeries> towardsCompleteSeriesWithOnePossibility(final StockOperation nextOperation) {
        return new StockOperationSeries(new ArrayList<StockOperation>() {{
            addAll(operations);
            add(nextOperation);
        }}, prices).createCompleteSeries();
    }

    private StockOperation lastOperation() {
        return operations.get(operations.size() - 1);
    }
}
