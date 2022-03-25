package com.tests.example;

import com.tests.example.business.Cart;
import com.tests.example.business.Cashier;
import com.tests.example.business.merchantProcessors.MerchantProcessor;
import com.tests.example.model.CreditCard;
import com.tests.example.model.Sale;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class CashierTestWithMocks {

    @Mock
    Cart cart;

    @Mock
    MerchantProcessor merchantProcessor;

    @Mock
    CreditCard creditCard;

    List<Sale> saleMockedList = new ArrayList<>();

    String client = "dummy-client";

    Cashier cashier;

    @Before
    public void initCashier(){

        Mockito.when(cart.isEmpty()).thenReturn(false);
        Mockito.when(creditCard.isExpiredOn(any())).thenReturn(false);

        cashier = new Cashier(LocalDate.now(), client, cart, creditCard, merchantProcessor, saleMockedList);
    }

    @Test
    public void assertCashierHasNotCheckOutAlready(){
        Mockito.when(cart.total()).thenReturn(10D);
        Sale sale = cashier.checkOut();

        Assertions.assertEquals(sale.total(), 10D);
        Assertions.assertFalse(cashier.salesBookIsEmpty());
    }

    @Test
    public void assertMerchantProcessorIsCalled(){
        Mockito.when(cart.total()).thenReturn(10D);
        Sale sale = cashier.checkOut();

        Mockito.verify(merchantProcessor, Mockito.times(1)).debit(creditCard, cart.total());
    }

    @Test
    public void assertMerchantProcessorNotCalled(){
        Mockito.when(cart.total()).thenReturn(0D);
        Sale sale = cashier.checkOut();

        Mockito.verify(merchantProcessor, Mockito.times(0)).debit(creditCard, cart.total());
    }



}
