package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.StockOperation.COOL;

public class SoldAtLastStockOperationSeries extends StockOperationSeries {
    public SoldAtLastStockOperationSeries(List<Integer> prices, List<StockOperation> operations, Integer sum, Account account) {
        super(prices, operations, sum, account);
    }

    @Override
    public List<StockOperationSeries> fromIncompleteToCompleteSeries() {
        return new CanPassAndBuyStockOperationSeries(
                prices, new ArrayList<StockOperation>(operations) {{
                    add(COOL);
                }},
                sum, account).towardsCompleteSeries();
    }
}
