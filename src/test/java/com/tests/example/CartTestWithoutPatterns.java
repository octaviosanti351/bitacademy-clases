package com.tests.example;

import com.tests.example.business.Cart;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CartTestWithoutPatterns {


    @Test
    public void assertCartQuantityLessThanOne(){
         Map<Object,Double> testCatalog = new HashMap<>();
        testCatalog.put("717029276-9", 150D);

         Cart cart = new Cart(testCatalog);
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
        Map<Object,Double> testCatalog = new HashMap<>();
        testCatalog.put("717029276-9", 150D);

        Cart cart = new Cart(testCatalog);
        try {
            cart.add("717029276-9", 1);
            assertFalse(cart.isEmpty());
        }
        catch (RuntimeException ex){
            fail();
        }
    }

}
