package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.StockOperation.*;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class StockOperationSeries {

    private final List<Integer> prices;
    private final List<StockOperation> operations;
    private final Integer sum;
    private boolean hasNotSold;

    public StockOperationSeries(List<StockOperation> operations, List<Integer> prices, Integer sum, boolean hasNotSold) {
        this.operations = operations;
        this.prices = prices;
        this.sum = sum;
        this.hasNotSold = hasNotSold;
    }

    public Integer sum() {
        return sum;
    }

    public List<StockOperation> operations() {
        return operations;
    }

    public List<StockOperationSeries> towardsCompleteSeries() {
        if (hasNoPrice())
            return emptyList();

        if (isSeriesComplete())
            return asList(this);

        if (isSellLastOperation())
            return towardsCompleteSeriesWith(COOL);

        if (hasNotSold)
            return towardsCompleteSeriesWith(SELL, PASS);

        if (isBuyWillBeALost())
            return towardsCompleteSeriesWith(PASS);

        return towardsCompleteSeriesWith(PASS, BUY);
    }

    private boolean isSellLastOperation() {
        return operations.size() > 0 &&
                operations.get(operations.size() - 1) == SELL;
    }

    private ArrayList<StockOperationSeries> towardsCompleteSeriesWith(final StockOperation... nextOperations) {
        return new ArrayList<StockOperationSeries>() {{
            for (final StockOperation nextOperation : nextOperations)
                addAll(stockOperationSeriesWith(nextOperation).towardsCompleteSeries());
        }};
    }

    private boolean isBuyWillBeALost() {
        return isLastOperation() || hasBuyAtHigherPrice();
    }

    private boolean hasBuyAtHigherPrice() {
        return priceOfNextOperation() > priceOfNextNextOperation();
    }

    private Integer priceOfNextNextOperation() {
        return prices.get(operations.size() + 1);
    }

    private boolean isLastOperation() {
        return operations.size() == prices.size() - 1;
    }

    private StockOperationSeries stockOperationSeriesWith(final StockOperation nextOperation) {
        return new StockOperationSeries(
                new ArrayList<StockOperation>(operations) {{
                    add(nextOperation);
                }},
                prices,
                sum + profitOf(nextOperation),
                nextHasNotSold(nextOperation, hasNotSold));
    }

    private int profitOf(StockOperation operation) {
        switch (operation) {
            case BUY:
                return -priceOfNextOperation();
            case SELL:
                return priceOfNextOperation();
            default:
                return 0;
        }
    }

    private Integer priceOfNextOperation() {
        return prices.get(operations.size());
    }

    private boolean nextHasNotSold(StockOperation nextOperation, boolean hasNotSold) {
        switch (nextOperation) {
            case BUY:
                return true;
            case SELL:
                return false;
            default:
                return hasNotSold;
        }
    }

    private boolean hasNoPrice() {
        return prices.isEmpty();
    }

    private boolean isSeriesComplete() {
        return operations.size() == prices.size();
    }

}
