package com.mobkata;

import org.junit.Test;

import java.util.function.Consumer;

import static com.mobkata.TestAccountHelper.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class TestAccountWithNotEnoughBalance {

    Consumer mockConsumer = mock(Consumer.class);

    @Test
    public void buy_with_not_enough_balance_should_not_call_the_consumer() {
        Account account = new Account(0);

        account.buy(PRICE, mockConsumer);

        verify(mockConsumer, never()).accept(any(Account.class));
    }

    @Test
    public void buy_with_enough_balance_should_call_the_consumer() {
        Account account = new Account(ENOUGH_BALANCE);

        account.buy(PRICE, mockConsumer);

        verify(mockConsumer).accept(any(Account.class));
    }

}
