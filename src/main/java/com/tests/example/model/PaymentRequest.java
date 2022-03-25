package com.tests.example.model;

import com.tests.example.business.Cart;

public class PaymentRequest {
    private Cart cart;
    private CreditCard creditCard;

    public PaymentRequest(Cart cart, CreditCard creditCard) {
        this.cart = cart;
        this.creditCard = creditCard;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}
