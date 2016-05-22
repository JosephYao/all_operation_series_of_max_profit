package com.mobkata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mobkata.ProfitableStockOperation.*;
import static com.mobkata.StockOperation.*;
import static java.util.Collections.emptyList;
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

        if (operationsOfLastEquals(1, BUY))
            return towardsCompleteSeriesWith(SELL, PASS);

        if (operationsOfLastEquals(2, BUY, PASS))
            return towardsCompleteSeriesWith(SELL, PASS);

        if (operationsOfLastEquals(3, BUY, PASS, PASS))
            return towardsCompleteSeriesWith(SELL);

        return towardsCompleteSeriesWith(PASS, BUY);
    }

    private boolean operationsOfLastEquals(int number, StockOperation... operations) {
        if(this.operations.size() < number)
            return false;

        boolean result = true;
        for (StockOperation operation : operations)
            result &= operationOfLastEquals(number--, operation);
        return result;
    }

    private boolean operationOfLastEquals(int position, StockOperation operation) {
        return operations.get(operations.size() - position) == operation;
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
