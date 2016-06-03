package com.mobkata.account;

import org.junit.Test;

import java.util.function.Consumer;

import static com.mobkata.account.TestAccountHelper.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class TestAccountBuyWithNotEnoughBalance {

    @Test
    public void buy_with_not_enough_balance_should_not_call_the_consumer() {
        Account account = new Account(0);
        Consumer mockConsumer = mock(Consumer.class);

        account.buy(PRICE, mockConsumer);

        verify(mockConsumer, never()).accept(any(Account.class));
    }

}
