package com.tests.example;

import com.tests.example.business.Cart;
import com.tests.example.doubles.BookStoreTestObjects;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(MockitoJUnitRunner.class)
public class CartTestWithFactoryMethod {


    BookStoreTestObjects bookStoreTestObjects = new BookStoreTestObjects();

    public Cart getValidCart(){
        Map<Object,Double> testCatalog = new HashMap<>();
        testCatalog.put(bookStoreTestObjects.productSellByBookStore() , bookStoreTestObjects.productSellByBookStorePrice());

        Cart cart = new Cart(testCatalog);
        cart.setValidUser(true);

        return cart;
    }

    @Test
    public void assertCartQuantityLessThanOne(){

        try {
            Cart cart = getValidCart();
            cart.add(bookStoreTestObjects.productSellByBookStore(), 0);
            fail();
        }
        catch (RuntimeException ex){
            assertEquals(Cart.PRODUCT_QUANTITY_MUST_BE_STRICTLY_POSITIVE, ex.getMessage());
        }
    }

    @Test
    public void assertCartQuantityMoreThanOne(){
        try {
            Cart cart = getValidCart();
            cart.add(bookStoreTestObjects.productSellByBookStore(), 1);
            assertFalse(cart.isEmpty());
        }
        catch (RuntimeException ex){
            fail();
        }
    }

    @Test
    public void assertCartProductIsNotSellInSupermarket(){
        try {
            Cart cart = getValidCart();
            cart.add(bookStoreTestObjects.productNotSellByBookStore(), 1);
            fail();
        }
        catch (RuntimeException ex){
            assertEquals(Cart.PRODUCT_IS_NOT_SELL_BY_SUPERMARKET, ex.getMessage());
        }
    }


}
