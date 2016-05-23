package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.StockOperation.*;

public class NotSoldYetStockOperationSeries extends StockOperationSeries {
    public NotSoldYetStockOperationSeries(List<Integer> prices, List<StockOperation> operations, Integer sum) {
        super(prices, operations, sum);
    }

    @Override
    public List<StockOperationSeries> fromIncompleteToCompleteSeries() {
        return new ArrayList<StockOperationSeries>() {{
            addAll(new SoldAtLastStockOperationSeries(
                    prices,
                    new ArrayList<StockOperation>(operations) {{
                        add(SELL);
                    }},
                    sum + priceOfNextOperation()).towardsCompleteSeries());
            addAll(createTowardsCompleteSeries(
                    prices,
                    new ArrayList<StockOperation>(operations) {{
                        add(PASS);
                    }},
                    sum,
                    true));
        }};
    }
}
