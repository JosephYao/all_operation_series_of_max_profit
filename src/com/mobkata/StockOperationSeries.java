package com.mobkata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mobkata.StockOperation.*;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class StockOperationSeries {

    private final List<Integer> prices;
    private final List<StockOperation> operations;
    private final List<ProfitableStockOpertion> profitableStockOpertions;

    public StockOperationSeries(List<StockOperation> operations, List<Integer> prices) {
        this.operations = operations;
        this.prices = prices;
        this.profitableStockOpertions = operations.stream().
                map(operation -> ProfitableStockOpertion.create(operation, operations, prices)).
                collect(toList());
    }

    public Integer sum() {
        return profitableStockOpertions.stream().
                mapToInt(profitableStockOpertion -> profitableStockOpertion.profit()).
                sum();
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
