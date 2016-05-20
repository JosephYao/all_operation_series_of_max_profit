package com.mobkata;

import java.util.List;

public class StockOperationSeries {

    private List<Integer> prices;
    private List<StockOperation> operations;

    public StockOperationSeries(List<StockOperation> operations, List<Integer> prices) {
        this.operations = operations;
        this.prices = prices;
    }

    public Integer sum() {
        int sum = 0;

        for (int i = 0; i < prices.size(); i++) {
            switch (operations.get(i)) {
                case PASS:
                    break;
                case BUY:
                    sum -= prices.get(i);
                    break;
                case SELL:
                    sum += prices.get(i);
                    break;
            }
        }

        return sum;
    }

    public List<StockOperation> operations() {
        return operations;
    }
}
