package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.ProfitableStockOperation.profitableStockOperation;
import static com.mobkata.StockOperation.*;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.lastIndexOfSubList;
import static java.util.stream.IntStream.range;

public class StockOperationSeries {

    private final List<Integer> prices;
    private final List<StockOperation> operations;
    private final List<ProfitableStockOperation> profitableStockOperations;

    public StockOperationSeries(List<StockOperation> operations, List<Integer> prices, List<ProfitableStockOperation> profitableStockOperations) {
        this.operations = operations;
        this.prices = prices;
        this.profitableStockOperations = profitableStockOperations;
    }

    public Integer sum() {
        return profitableStockOperations.stream().
                mapToInt(profitableStockOperation -> profitableStockOperation.profit()).
                sum();
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

        if (hasNotSold())
            return towardsCompleteSeriesWith(SELL, PASS);

        return towardsCompleteSeriesWith(PASS, BUY);
    }

    private boolean hasNotSold() {
        return range(1, operations.size() + 1).anyMatch(number ->
                        operationsOfLastEquals(number, lastOperationsOfNotSold(number)));
    }

    private StockOperation[] lastOperationsOfNotSold(int number) {
        StockOperation[] lastOperations = new StockOperation[number];
        lastOperations[0] = BUY;
        range(1, number).forEach(i -> lastOperations[i] = PASS);
        return lastOperations;
    }

    private boolean operationsOfLastEquals(int number, StockOperation... lastOperations) {
        return lastIndexOfSubList(operations, asList(lastOperations)) == operations.size() - number;
    }

    private ArrayList<StockOperationSeries> towardsCompleteSeriesWith(final StockOperation... nextOperations) {
        return new ArrayList<StockOperationSeries>() {{
            for (final StockOperation nextOperation : nextOperations)
                addAll(stockOperationSeriesWith(nextOperation).towardsCompleteSeries());
        }};
    }

    private StockOperationSeries stockOperationSeriesWith(final StockOperation nextOperation) {
        return new StockOperationSeries(
                new ArrayList<StockOperation>(operations) {{
                    add(nextOperation);
                }},
                prices,
                new ArrayList<ProfitableStockOperation>(profitableStockOperations) {{
                    add(profitableStockOperation(nextOperation, operations, prices));
                }});
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
