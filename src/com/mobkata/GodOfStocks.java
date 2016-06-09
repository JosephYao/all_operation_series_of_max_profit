package com.mobkata;

import com.mobkata.account.Account;

import java.util.List;

import static com.mobkata.StockOperationSeries.allStockOperationSeries;
import static java.util.stream.Collectors.toList;

public class GodOfStocks {
    private final int balance;

    public GodOfStocks(int balance) {
        this.balance = balance;
    }

    public List<List<StockOperation>> operationsForMaxProfit(List<Integer> prices) {
        List<StockOperationSeries> allStockOperationSeries = allStockOperationSeries(prices, new Account(balance));

        int maxProfit = allStockOperationSeries.stream().
                mapToInt(StockOperationSeries::sum).max().orElse(0);

        return allStockOperationSeries.stream().
                filter(series -> series.sum() == maxProfit).
                map(StockOperationSeries::operations).
                collect(toList());
    }

    public int maxProfit(List<Integer> prices) {
        return allStockOperationSeries(prices, new Account(100)).stream().
                mapToInt(StockOperationSeries::sum).max().orElse(0);
    }
}
