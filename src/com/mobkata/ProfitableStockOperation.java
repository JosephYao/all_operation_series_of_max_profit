package com.mobkata;

import java.util.List;

public class ProfitableStockOperation {
    private final StockOperation operation;
    private final Integer price;

    public ProfitableStockOperation(StockOperation operation, Integer price) {
        this.operation = operation;
        this.price = price;
    }

    public int profit() {
        switch (operation) {
            case BUY:
                return -price;
            case SELL:
                return price;
            default:
                return 0;
        }
    }

    public static ProfitableStockOperation create(StockOperation operation, List<StockOperation> operations, List<Integer> prices) {
        return new ProfitableStockOperation(operation, priceOf(operation, operations, prices));
    }

    private static Integer priceOf(StockOperation operation, List<StockOperation> operations, List<Integer> prices) {
        return prices.get(operations.indexOf(operation));
    }

}
