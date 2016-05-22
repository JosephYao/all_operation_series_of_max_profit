package com.mobkata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static com.mobkata.ProfitableStockOperation.create;
import static com.mobkata.StockOperation.*;
import static java.util.Collections.emptyList;
import static java.util.Collections.lastIndexOfSubList;
import static java.util.stream.Collectors.toList;

public class StockOperationSeries {

    private final List<Integer> prices;
    private final List<StockOperation> operations;
    private final List<ProfitableStockOperation> profitableStockOperations;

    public StockOperationSeries(List<StockOperation> operations, List<Integer> prices) {
        this.operations = operations;
        this.prices = prices;
        this.profitableStockOperations = operations.stream().
                map(operation -> create(operation, operations, prices)).
                collect(toList());
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
            return Arrays.asList(this);

        if (hasNoOperation())
            return towardsCompleteSeriesWith(PASS, BUY);

        if (operationsOfLastEquals(1, SELL))
            return towardsCompleteSeriesWith(COOL);

        if (hasNotSold())
            return towardsCompleteSeriesWith(SELL, PASS);

        return towardsCompleteSeriesWith(PASS, BUY);
    }

    private boolean hasNotSold() {
        return IntStream.range(1, operations.size() + 1).
                anyMatch(number ->
                        operationsOfLastEquals(number, lastOperationsOfNotSold(number)));
    }

    private StockOperation[] lastOperationsOfNotSold(int number) {
        StockOperation[] lastOperations = new StockOperation[number];
        lastOperations[0] = BUY;
        IntStream.range(1, number).forEach(i -> lastOperations[i] = PASS);
        return lastOperations;
    }

    private boolean operationsOfLastEquals(int number, StockOperation... lastOperations) {
        return lastIndexOfSubList(operations, Arrays.asList(lastOperations)) == operations.size() - number;
    }

    private ArrayList<StockOperationSeries> towardsCompleteSeriesWith(final StockOperation... nextOperations) {
        return new ArrayList<StockOperationSeries>() {{
            for (final StockOperation nextOperation : nextOperations)
                addAll(new StockOperationSeries(new ArrayList<StockOperation>(operations) {{
                    add(nextOperation);
                }}, prices).towardsCompleteSeries());
        }};
    }

    private boolean hasNoPrice() {
        return prices.isEmpty();
    }

    private boolean hasNoOperation() {
        return operations.size() == 0;
    }

    private boolean isSeriesComplete() {
        return operations.size() == prices.size();
    }

}
