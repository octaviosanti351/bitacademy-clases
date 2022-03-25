package com.tests.example;

import com.tests.example.business.Cart;
import com.tests.example.business.Cashier;
import com.tests.example.business.merchantProcessors.MerchantProcessor;
import com.tests.example.doubles.BookStoreTestObjects;
import com.tests.example.model.Sale;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;


import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitPlatform.class)
public class CashierTestWithStubs {

    BookStoreTestObjects bookStoreTestObjects = new BookStoreTestObjects();


    public Cashier getCashierWithoutCheckOut(){
        Cart cart = bookStoreTestObjects.getFakeCartWithProduct();
        MerchantProcessor merchantProcessor = bookStoreTestObjects.getDummyMerchantProcessor();

        return new Cashier(LocalDate.now(), "dummy-client", cart, bookStoreTestObjects.getCreditCardNotExpired(), merchantProcessor, new ArrayList<Sale>());
    }

    public Cashier getCheckedOutCashier(){
        Cart cart = bookStoreTestObjects.getFakeCartWithProduct();
        MerchantProcessor merchantProcessor = bookStoreTestObjects.getDummyMerchantProcessor();

        Cashier cashier = new Cashier(LocalDate.now(), "dummy-client", cart, bookStoreTestObjects.getCreditCardNotExpired(), merchantProcessor, new ArrayList<Sale>());
        cashier.checkOut();
        return cashier;
    }

    @Test
    public void assertCashierHasCheckedOutBefore(){
        Cashier cashier = getCheckedOutCashier();

        try {
            cashier.checkOut();
            fail();
        }
        catch (RuntimeException ex){
            assertEquals(Cashier.CAN_CHECKOUT_ONLY_ONCE, ex.getMessage());
        }
    }

    @Test
    public void assertCashierHasNotCheckOutAlready(){
        Cashier cashier = getCashierWithoutCheckOut();
        try {
            cashier.checkOut();
            assertFalse(cashier.salesBookIsEmpty());
        }
        catch (RuntimeException ex){
           fail();
        }
    }
}
