package com.mobkata;

import java.util.List;

import static com.mobkata.StockOperation.SELL;
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
        return create(prices, emptyList(), 0, false).towardsCompleteSeries();
    }

    protected static List<StockOperationSeries> createTowardsCompleteSeries(List<Integer> prices, List<StockOperation> operations, Integer sum, boolean hasNotSold) {
        return create(prices, operations, sum, hasNotSold).towardsCompleteSeries();
    }

    private static StockOperationSeries create(List<Integer> prices, List<StockOperation> operations, Integer sum, boolean hasNotSold) {
        if (hasNoPrice(prices))
            return new EmptyStockOperationSeries();

        if (isCompleteSeries(prices, operations))
            return new CompleteStockOperationSeries(operations, sum);

        if (isSoldAtLast(operations))
            return new SoldAtLastStockOperationSeries(prices, operations, sum);

        if (hasNotSold)
            return new NotSoldYetStockOperationSeries(prices, operations, sum);

        if (isBuyWillBeALost(prices, operations))
            return new CanPassOnlyStockOperationSeries(prices, operations, sum);

        return new CanPassAndBuyStockOperationSeries(prices, operations, sum);
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

    private static Integer priceOfNextOperation(List<Integer> prices, List<StockOperation> operations) {
        return prices.get(operations.size());
    }

    protected Integer priceOfNextOperation() {
        return priceOfNextOperation(prices, operations);
    }

    public Integer sum() {
        return sum;
    }

    public List<StockOperation> operations() {
        return operations;
    }

    public abstract List<StockOperationSeries> towardsCompleteSeries();

}
