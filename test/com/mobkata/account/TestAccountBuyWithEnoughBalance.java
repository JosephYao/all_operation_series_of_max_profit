package com.mobkata.account;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.function.Consumer;

import static com.mobkata.account.TestAccountHelper.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestAccountBuyWithEnoughBalance {

    Account account = new Account(ENOUGH_BALANCE);
    Consumer mockConsumer = mock(Consumer.class);

    @Test
    public void buy_with_enough_balance_should_call_the_consumer() {
        account.buy(PRICE, mockConsumer);

        verify(mockConsumer).accept(any(Account.class));
    }

    @Test
    public void accepted_account_profit() {
        account.buy(PRICE, mockConsumer);

        assertEquals(-PRICE, consumedAccount().profit());
    }

    @Test
    public void accepted_account_balance() {
        account.buy(PRICE, mockConsumer);

        assertEquals(ENOUGH_BALANCE - PRICE, consumedAccount().balance());
    }

    private Account consumedAccount() {
        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);
        verify(mockConsumer).accept(captor.capture());
        return captor.getValue();
    }

}
