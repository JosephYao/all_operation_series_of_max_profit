package com.mobkata;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class CompleteStockOperationSeries extends StockOperationSeries {
    public CompleteStockOperationSeries(List<StockOperation> operations, Integer sum) {
        super(emptyList(), operations, sum, false);
    }

    @Override
    public List<StockOperationSeries> towardsCompleteSeries() {
        return asList(this);
    }
}
