package com.tests.example;

import com.tests.example.business.Cart;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class CartTestWithAntiPatternHiddenCall {

    private Cart cart;
    @Test
    public void assertCartQuantityLessThanOne(){
        Map<Object,Double> testCatalog = new HashMap<>();
        testCatalog.put("717029276-9", 150D);

        cart = new Cart(testCatalog);
        try {
            cart.add("717029276-9", 0);
            fail();
        }
        catch (RuntimeException ex){
            assertEquals("Product quantity must be strictly positive", ex.getMessage());
        }

        assertCartQuantityMoreThanOne();
    }

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
