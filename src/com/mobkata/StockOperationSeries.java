package com.mobkata;

import java.util.List;

import static java.util.Collections.emptyList;

public abstract class StockOperationSeries {

    protected final List<Integer> prices;
    protected final List<StockOperation> operations;
    protected final Integer sum;

    public StockOperationSeries(List<Integer> prices, List<StockOperation> operations, Integer sum) {
        this.operations = operations;
        this.prices = prices;
        this.sum = sum;
    }

    public static List<StockOperationSeries> allStockOperationSeries(List<Integer> prices) {
        return new CanPassAndBuyStockOperationSeries(prices, emptyList(), 0).towardsCompleteSeries();
    }

    protected Integer priceOfNextOperation() {
        return prices.get(operations.size());
    }

    public Integer sum() {
        return sum;
    }

    public List<StockOperation> operations() {
        return operations;
    }

    public List<StockOperationSeries> towardsCompleteSeries() {
        if (isComplete())
            return new CompleteStockOperationSeries(operations, sum).towardsCompleteSeries();

        return fromIncompleteToCompleteSeries();
    }

    private boolean isComplete() {
        return operations.size() == prices.size();
    }

    public List<StockOperationSeries> fromIncompleteToCompleteSeries() {
        throw new UnsupportedOperationException();
    }

}
