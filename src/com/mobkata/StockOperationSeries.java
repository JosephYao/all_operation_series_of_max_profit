package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.StockOperation.*;
import static java.util.Collections.emptyList;

public class StockOperationSeries {

    protected final List<Integer> prices;
    protected final List<StockOperation> operations;
    protected final Integer sum;
    protected boolean hasNotSold;

    public StockOperationSeries(List<Integer> prices, List<StockOperation> operations, Integer sum, boolean hasNotSold) {
        this.operations = operations;
        this.prices = prices;
        this.sum = sum;
        this.hasNotSold = hasNotSold;
    }

    public static List<StockOperationSeries> allStockOperationSeries(List<Integer> prices) {
        return create(prices, emptyList(), 0, false).towardsCompleteSeries();
    }

    protected static StockOperationSeries create(List<Integer> prices, List<StockOperation> operations, Integer sum, boolean hasNotSold) {
        if (hasNoPrice(prices))
            return new EmptyStockOperationSeries();

        if (isCompleteSeries(prices, operations))
            return new CompleteStockOperationSeries(operations, sum);

        if (isSoldAtLast(operations))
            return new SoldAtLastStockOperationSeries(prices, operations, sum);

        if (hasNotSold)
            return new NotSoldYetStockOperationSeries(prices, operations, sum);

        if (isBuyWillBeALost(prices, operations))
            return new PassOnlyStockOperationSeries(prices, operations, sum, hasNotSold);

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

    private static boolean isBuyWillBeALost(List<Integer> prices, List<StockOperation> operations) {
        return isLastOperation(prices, operations) || hasBuyAtHigherPrice(prices, operations);
    }

    private static boolean hasBuyAtHigherPrice(List<Integer> prices, List<StockOperation> operations) {
        return priceOfNextOperation(prices, operations) > priceOfNextNextOperation(prices, operations);
    }

    private static Integer priceOfNextNextOperation(List<Integer> prices, List<StockOperation> operations) {
        return prices.get(operations.size() + 1);
    }

    private static boolean isLastOperation(List<Integer> prices, List<StockOperation> operations) {
        return operations.size() == prices.size() - 1;
    }

    protected static Integer priceOfNextOperation(List<Integer> prices, List<StockOperation> operations) {
        return prices.get(operations.size());
    }

    public Integer sum() {
        return sum;
    }

    public List<StockOperation> operations() {
        return operations;
    }

    public List<StockOperationSeries> towardsCompleteSeries() {
        return towardsCompleteSeriesWith(PASS, BUY);
    }

    protected ArrayList<StockOperationSeries> towardsCompleteSeriesWith(final StockOperation... nextOperations) {
        return new ArrayList<StockOperationSeries>() {{
            for (final StockOperation nextOperation : nextOperations)
                addAll(createWithNextOperation(nextOperation).towardsCompleteSeries());
        }};
    }

    protected StockOperationSeries createWithNextOperation(final StockOperation nextOperation) {
        return create(
                prices, new ArrayList<StockOperation>(operations) {{
                    add(nextOperation);
                }},
                sum + profitOf(nextOperation),
                nextHasNotSold(nextOperation, hasNotSold));
    }

    protected int profitOf(StockOperation operation) {
        switch (operation) {
            case BUY:
                return -priceOfNextOperation(prices, operations);
            case SELL:
                return priceOfNextOperation(prices, operations);
            default:
                return 0;
        }
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
