package com.mobkata;

import com.mobkata.account.Account;

import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static java.util.Collections.emptyList;

public class EmptyStockOperationSeries extends StockOperationSeries {

    public EmptyStockOperationSeries() {
        super(emptyList(), emptyList(), new Account(MAX_VALUE));
    }

    @Override
    public List<StockOperationSeries> towardsCompleteSeries() {
        return emptyList();
    }

}
