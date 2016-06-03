package com.mobkata.account;

import org.junit.Test;

import java.util.function.Consumer;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestAccountBuyWithEnoughBalance {

    @Test
    public void buy_with_enough_balance_should_call_the_consumer() {
        Account account = new Account(TestAccountHelper.ENOUGH_BALANCE);
        Consumer mockConsumer = mock(Consumer.class);

        account.buy(TestAccountHelper.PRICE, mockConsumer);

        verify(mockConsumer).accept(any(Account.class));
    }

}
