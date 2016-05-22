package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.StockOperation.PASS;

public class CanPassOnlyStockOperationSeries extends StockOperationSeries {
    public CanPassOnlyStockOperationSeries(List<Integer> prices, List<StockOperation> operations, Integer sum) {
        super(prices, operations, sum);
    }

    @Override
    public List<StockOperationSeries> towardsCompleteSeries() {
        return createTowardsCompleteSeries(
                prices,
                new ArrayList<StockOperation>(operations) {{
                    add(PASS);
                }},
                sum,
                false);
    }
}
