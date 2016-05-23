package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.StockOperation.BUY;
import static com.mobkata.StockOperation.PASS;

public class CanPassAndBuyStockOperationSeries extends StockOperationSeries {
    public CanPassAndBuyStockOperationSeries(List<Integer> prices, List<StockOperation> operations, Integer sum) {
        super(prices, operations, sum);
    }

    @Override
    public List<StockOperationSeries> fromIncompleteToCompleteSeries() {
        return new ArrayList<StockOperationSeries>() {{
            addAll(createTowardsCompleteSeries(
                    prices,
                    new ArrayList<StockOperation>(operations) {{
                        add(PASS);
                    }},
                    sum,
                    false));
            addAll(createTowardsCompleteSeries(
                    prices,
                    new ArrayList<StockOperation>(operations) {{
                        add(BUY);
                    }},
                    sum - priceOfNextOperation(),
                    true));
        }};
    }
}
