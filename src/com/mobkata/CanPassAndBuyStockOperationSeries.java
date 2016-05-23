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
        if (isBuyWillBeALost())
            return new CanPassOnlyStockOperationSeries(prices, operations, sum).towardsCompleteSeries();

        return new ArrayList<StockOperationSeries>() {{
            addAll(new CanPassAndBuyStockOperationSeries(
                    prices,
                    new ArrayList<StockOperation>(operations) {{
                        add(PASS);
                    }},
                    sum).towardsCompleteSeries());
            addAll(new NotSoldYetStockOperationSeries(
                    prices,
                    new ArrayList<StockOperation>(operations) {{
                        add(BUY);
                    }},
                    sum - priceOfNextOperation()).towardsCompleteSeries());
        }};
    }

    private boolean isBuyWillBeALost() {
        return isLastOperation() || hasBuyAtHigherPrice();
    }

    @Override
    public List<StockOperationSeries> towardsCompleteSeries() {
        if (hasNoPrice())
            return new EmptyStockOperationSeries().towardsCompleteSeries();

        return super.towardsCompleteSeries();
    }

    private boolean isLastOperation() {
        return operations.size() == prices.size() - 1;
    }

    private boolean hasBuyAtHigherPrice() {
        return priceOfNextOperation() > priceOfNextNextOperation();
    }

    private boolean hasNoPrice() {
        return prices.isEmpty();
    }

    private Integer priceOfNextNextOperation() {
        return prices.get(operations.size() + 1);
    }

}
