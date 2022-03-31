package com.tests.example.business;

import com.tests.example.business.merchantProcessors.MerchantProcessor;
import com.tests.example.model.CreditCard;
import com.tests.example.model.Sale;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Cashier {
    public static final String CAN_NOT_CHECKOUT_EMPTY_CART = "Can not checkout an empty cart";
    public static final String CREDIT_CARD_EXPIRED = "Credit card is expired";
    public static final String CLIENT_IS_INVALID = "Client is invalid";
    public static final String CAN_CHECKOUT_ONLY_ONCE = "Can checkout only once";

    private Cart cart;
    private MerchantProcessor merchantProcessor;
    private CreditCard creditCard;
    private boolean hasCheckedOut;
    private List<Sale> salesBook;
    private Object client;

    public Cashier(LocalDate today,
                   Object client,
                   Cart cart,
                   CreditCard creditCard,
                   MerchantProcessor merchantProcessor,
                   List<Sale> salesBook) {

        assertCartIsNotEmpty(cart);
        assertCreditCardIsNotExpired(creditCard, today);

        this.client = client;
        this.cart = cart;
        this.creditCard = creditCard;
        this.merchantProcessor = merchantProcessor;
        this.salesBook = salesBook;

        this.hasCheckedOut = false;
    }

    //todo: correr tests con coverage y agregar los que falten!!

    public Sale checkOut() {
        assertHasNotCheckOutAlready();
        try {
            double total = cart.total();
            if(cart.total() > 0) {
                merchantProcessor.debit(creditCard, total);
            }
            Sale sale = new Sale(UUID.randomUUID(),this,total);
            salesBook.add(sale);
            return sale;
        } finally {
            hasCheckedOut = true;
        }
    }

    public void assertHasNotCheckOutAlready() {
        if (hasCheckedOut) throw new RuntimeException(CAN_CHECKOUT_ONLY_ONCE);
    }

    public void assertCreditCardIsNotExpired(CreditCard creditCard, LocalDate today) {
        if (creditCard.isExpiredOn(today)) throw new RuntimeException(CREDIT_CARD_EXPIRED);
    }

    public void assertCartIsNotEmpty(Cart cart) {
        if (cart.isEmpty()) throw new RuntimeException(CAN_NOT_CHECKOUT_EMPTY_CART);
    }

    public boolean salesBookIsEmpty() {
        return salesBook.isEmpty();
    }
}
