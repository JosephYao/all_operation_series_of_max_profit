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

    public List<StockOperationSeries> towardsCompleteSeries() {
        if (hasNoPrice())
            return emptyList();

        if (isSeriesComplete())
            return Arrays.asList(this);

        if (hasNoOperation())
            return towardsCompleteSeriesWith(PASS, BUY);

        if (lastOperation() == BUY)
            return towardsCompleteSeriesWith(SELL);

        if (lastOperation() == SELL)
            return towardsCompleteSeriesWith(COOL);

        return towardsCompleteSeriesWith(PASS, BUY);
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

    private StockOperation lastOperation() {
        return operations.get(operations.size() - 1);
    }
}
