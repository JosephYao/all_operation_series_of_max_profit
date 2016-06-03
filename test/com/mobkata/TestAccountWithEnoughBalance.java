package com.mobkata;

import org.junit.Test;

import java.util.function.Consumer;

import static com.mobkata.TestAccountHelper.ENOUGH_BALANCE;
import static com.mobkata.TestAccountHelper.PRICE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestAccountWithEnoughBalance {

    Account account = new Account(ENOUGH_BALANCE);

    @Test
    public void profit_is_0_for_a_new_account() {
        assertEquals(0, account.profit());
    }

    @Test
    public void profit_when_buy_with_enough_balance() {
        account = account.buy(PRICE);

        assertEquals(-PRICE, account.profit());
    }

    @Test
    public void profit_when_after_sell_and_pass_one_day() {
        account = account.buy(PRICE);

        account = account.sell(PRICE + 1).pass();

        assertEquals(1, account.profit());
    }

    @Test
    public void buy_with_enough_balance_should_call_the_consumer() {
        Consumer mockConsumer = mock(Consumer.class);

        account.buy(PRICE, mockConsumer);

        verify(mockConsumer).accept(any(Account.class));
    }

}
