package com.tests.example;

import com.tests.example.business.Cart;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(MockitoJUnitRunner.class)
public class CartTestWithSetupPattern {
    private static Cart cart;

    @BeforeAll
    public static void setup(){
        Map<Object,Double> testCatalog = new HashMap<>();
        testCatalog.put("717029276-9", 150D);

        cart = new Cart(testCatalog);
        cart.setValidUser(true);
    }

    @Test
    public void assertCartQuantityLessThanOne(){

        try {
            cart.add("717029276-9", 0);
            fail();
        }
        catch (RuntimeException ex){
            assertEquals("Product quantity must be strictly positive", ex.getMessage());
        }
    }

    @Test
    public void assertCartQuantityMoreThanOne(){
        try {
            cart.add("717029276-9", 1);
            assertFalse(cart.isEmpty());
        }
        catch (RuntimeException ex){
            fail();
        }
    }
}
