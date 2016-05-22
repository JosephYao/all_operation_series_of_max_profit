package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.StockOperation.COOL;

public class SoldAtLastStockOperationSeries extends StockOperationSeries {
    public SoldAtLastStockOperationSeries(List<Integer> prices, List<StockOperation> operations, Integer sum) {
        super(prices, operations, sum, false);
    }

    @Override
    public List<StockOperationSeries> towardsCompleteSeries() {
        return create(
                prices, new ArrayList<StockOperation>(operations) {{
                    add(COOL);
                }},
                sum,
                false).towardsCompleteSeries();
    }
}
