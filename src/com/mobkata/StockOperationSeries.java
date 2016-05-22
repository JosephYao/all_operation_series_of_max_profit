package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.ProfitableStockOperation.profitableStockOperation;
import static com.mobkata.StockOperation.*;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.lastIndexOfSubList;

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

        if (hasNoOperation())
            return towardsCompleteSeriesWith(PASS, BUY);

        if (operationsOfLastEquals(1, SELL))
            return towardsCompleteSeriesWith(COOL);

        if (hasNotSold)
            return towardsCompleteSeriesWith(SELL, PASS);

        return towardsCompleteSeriesWith(PASS, BUY);
    }

    private boolean operationsOfLastEquals(int number, StockOperation... lastOperations) {
        return lastIndexOfSubList(operations, asList(lastOperations)) == operations.size() - number;
    }

    private ArrayList<StockOperationSeries> towardsCompleteSeriesWith(final StockOperation... nextOperations) {
        return new ArrayList<StockOperationSeries>() {{
            for (final StockOperation nextOperation : nextOperations) {
                if (nextOperation == BUY && operations.size() < prices.size() - 1 && profitableStockOperation(nextOperation, operations, prices).price() > prices.get(operations.size() + 1))
                    continue;
                addAll(stockOperationSeriesWith(nextOperation).towardsCompleteSeries());
            }
        }};
    }

    private StockOperationSeries stockOperationSeriesWith(final StockOperation nextOperation) {
        return new StockOperationSeries(
                new ArrayList<StockOperation>(operations) {{
                    add(nextOperation);
                }},
                prices,
                sum + profitableStockOperation(nextOperation, operations, prices).profit(),
                nextHasNotSold(nextOperation, hasNotSold));
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

    private boolean hasNoOperation() {
        return operations.isEmpty();
    }

    private boolean isSeriesComplete() {
        return operations.size() == prices.size();
    }

}
