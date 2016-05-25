package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.StockOperation.PASS;

public class CanPassOnlyStockOperationSeries extends StockOperationSeries {
    public CanPassOnlyStockOperationSeries(List<Integer> prices, List<StockOperation> operations, Integer sum, Account account) {
        super(prices, operations, sum, account);
    }

    @Override
    public List<StockOperationSeries> fromIncompleteToCompleteSeries() {
        return new CanPassAndBuyStockOperationSeries(
                prices,
                new ArrayList<StockOperation>(operations) {{
                    add(PASS);
                }},
                sum, account).towardsCompleteSeries();
    }
}
