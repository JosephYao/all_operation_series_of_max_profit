package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.StockOperation.*;

public class NotSoldYetStockOperationSeries extends StockOperationSeries {
    public NotSoldYetStockOperationSeries(List<Integer> prices, List<StockOperation> operations, Integer sum) {
        super(prices, operations, sum, true);
    }

    @Override
    public List<StockOperationSeries> towardsCompleteSeries() {
        return new ArrayList<StockOperationSeries>() {{
            addAll(create(
                    prices,
                    new ArrayList<StockOperation>(operations) {{
                        add(SELL);
                    }},
                    sum + priceOfNextOperation(prices, operations),
                    false).towardsCompleteSeries());
            addAll(create(
                    prices,
                    new ArrayList<StockOperation>(operations) {{
                        add(PASS);
                    }},
                    sum,
                    true).towardsCompleteSeries());
        }};
    }
}
