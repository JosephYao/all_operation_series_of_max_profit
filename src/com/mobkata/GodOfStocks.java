package com.mobkata;

import java.util.ArrayList;
import java.util.List;

import static com.mobkata.StockOperation.*;
import static java.util.Arrays.asList;

public class GodOfStocks {
    public List<List<StockOperation>> operationsForMaxProfit(List<Integer> prices) {
        final List<StockOperation> stockOperations = new ArrayList<>();

        prices.stream().forEach( price -> stockOperations.add(PASS));

        return asList(stockOperations);
    }
}
