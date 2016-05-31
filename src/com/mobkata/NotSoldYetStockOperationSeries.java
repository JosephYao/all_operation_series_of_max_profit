package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.StockOperation.*;

public class NotSoldYetStockOperationSeries extends StockOperationSeries {
    public NotSoldYetStockOperationSeries(List<Integer> prices, List<StockOperation> operations, Account account) {
        super(prices, operations, account);
    }

    @Override
    public List<StockOperationSeries> fromIncompleteToCompleteSeries() {
        return new ArrayList<StockOperationSeries>() {{
            addAll(new SoldAtLastStockOperationSeries(
                    prices,
                    new ArrayList<StockOperation>(operations) {{
                        add(SELL);
                    }},
                    account.sell(priceOfNextOperation())).towardsCompleteSeries());
            addAll(new NotSoldYetStockOperationSeries(
                    prices,
                    new ArrayList<StockOperation>(operations) {{
                        add(PASS);
                    }},
                    account.pass()).towardsCompleteSeries());
        }};
    }
}
