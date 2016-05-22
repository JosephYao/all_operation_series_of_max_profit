package com.mobkata;

import java.util.List;

import static java.util.Collections.emptyList;

public class EmptyStockOperationSeries extends StockOperationSeries {

    public EmptyStockOperationSeries() {
        super(emptyList(), emptyList(), 0, false);
    }

    @Override
    public List<StockOperationSeries> towardsCompleteSeries() {
        return emptyList();
    }
}
