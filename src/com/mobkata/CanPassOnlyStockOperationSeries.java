package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.StockOperation.PASS;

public class CanPassOnlyStockOperationSeries extends StockOperationSeries {
    public CanPassOnlyStockOperationSeries(List<Integer> prices, List<StockOperation> operations, Account account) {
        super(prices, operations, account);
    }

    @Override
    public List<StockOperationSeries> fromIncompleteToCompleteSeries() {
        return new CanPassAndBuyStockOperationSeries(
                prices,
                new ArrayList<StockOperation>(operations) {{
                    add(PASS);
                }},
                account).towardsCompleteSeries();
    }
}
