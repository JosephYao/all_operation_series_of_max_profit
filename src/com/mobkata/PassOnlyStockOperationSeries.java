package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.StockOperation.PASS;

public class PassOnlyStockOperationSeries extends StockOperationSeries {
    public PassOnlyStockOperationSeries(List<Integer> prices, List<StockOperation> operations, Integer sum, boolean hasNotSold) {
        super(prices, operations, sum, hasNotSold);
    }

    @Override
    public List<StockOperationSeries> towardsCompleteSeries() {
        return create(
                prices,
                new ArrayList<StockOperation>(operations) {{
                    add(PASS);
                }},
                sum,
                hasNotSold).towardsCompleteSeries();
    }
}
