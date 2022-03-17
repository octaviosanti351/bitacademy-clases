package com.tests.example;

import com.tests.example.business.Cart;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class CartTestWithFactoryMethod {


    public Cart getValidCart(){
        Map<Object,Double> testCatalog = new HashMap<>();
        testCatalog.put("717029276-9", 150D);

        Cart cart = new Cart(testCatalog);
        cart.setValidUser(true);

        return cart;
    }

    @Test
    public void assertCartQuantityLessThanOne(){

        try {
            Cart cart = getValidCart();
            cart.add("717029276-9", 0);
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
            cart.add("717029276-9", 1);
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
            cart.add("717029276-xxx", 1);
            fail();
        }
        catch (RuntimeException ex){
            assertEquals(Cart.PRODUCT_IS_NOT_SELL_BY_SUPERMARKET, ex.getMessage());
        }
    }


}
