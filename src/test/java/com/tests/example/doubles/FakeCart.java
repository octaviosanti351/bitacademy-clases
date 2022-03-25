package com.tests.example.doubles;

import com.tests.example.business.Cart;

import java.util.Map;

public class FakeCart extends Cart {

    public FakeCart(Map<Object, Double> catalog) {
        super(catalog);
        this.validUser = true;
    }

}
