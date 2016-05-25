package com.mobkata;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

public class CompleteStockOperationSeries extends StockOperationSeries {
    public CompleteStockOperationSeries(List<StockOperation> operations, Integer sum, Account account) {
        super(emptyList(), operations, sum, account);
    }

    @Override
    public List<StockOperationSeries> towardsCompleteSeries() {
        return Arrays.asList(this);
    }
}
