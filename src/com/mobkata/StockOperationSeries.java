package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.StockOperation.*;
import static java.util.Collections.emptyList;

public class StockOperationSeries {

    private final List<Integer> prices;
    private final List<StockOperation> operations;
    private final Integer sum;
    private boolean hasNotSold;

    public StockOperationSeries(List<Integer> prices, List<StockOperation> operations, Integer sum, boolean hasNotSold) {
        this.operations = operations;
        this.prices = prices;
        this.sum = sum;
        this.hasNotSold = hasNotSold;
    }

    public static List<StockOperationSeries> allStockOperationSeries(List<Integer> prices) {
        return create(prices, emptyList(), 0, false).towardsCompleteSeries();
    }

    private static StockOperationSeries create(List<Integer> prices, List<StockOperation> operations, Integer sum, boolean hasNotSold) {
        if (hasNoPrice(prices))
            return new EmptyStockOperationSeries();

        if (isCompleteSeries(prices, operations))
            return new CompleteStockOperationSeries(operations, sum);

        if (isSoldAtLast(operations))
            return new SoldAtLastStockOperationSeries(prices, operations, sum);

        return new StockOperationSeries(prices, operations, sum, hasNotSold);
    }

    private static boolean isSoldAtLast(List<StockOperation> operations) {
        return operations.size() > 0 && operations.get(operations.size() - 1) == SELL;
    }

    private static boolean isCompleteSeries(List<Integer> prices, List<StockOperation> operations) {
        return operations.size() == prices.size();
    }

    private static boolean hasNoPrice(List<Integer> prices) {
        return prices.isEmpty();
    }

    public Integer sum() {
        return sum;
    }

    public List<StockOperation> operations() {
        return operations;
    }

    public List<StockOperationSeries> towardsCompleteSeries() {
        if (hasNotSold)
            return towardsCompleteSeriesWith(SELL, PASS);

        if (isBuyWillBeALost())
            return towardsCompleteSeriesWith(PASS);

        return towardsCompleteSeriesWith(PASS, BUY);
    }

    protected ArrayList<StockOperationSeries> towardsCompleteSeriesWith(final StockOperation... nextOperations) {
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
        return create(
                prices, new ArrayList<StockOperation>(operations) {{
                    add(nextOperation);
                }},
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

}
