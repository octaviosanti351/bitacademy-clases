package com.tests.example.doubles;

import com.tests.example.business.Cart;
import com.tests.example.business.merchantProcessors.MerchantProcessor;
import com.tests.example.model.CreditCard;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

public class BookStoreTestObjects {

    public Object productSellByBookStore() {
        return "productSellByBookStore";
    }

    public String validCreditCardNumber() {
        return "validCreditCardNumber";
    }

    public String validCreditCardOwner() {
        return "validCreditCardOwner";
    }

    public double productSellByBookStorePrice() {
        return 10D;
    }

    public Object productNotSellByBookStore() {
        return "productNotSellByBookStore";
    }

    public Cart getFakeCartWithProduct(){

        Map<Object,Double> testCatalog = new HashMap<>();
        testCatalog.put(productSellByBookStore() , productSellByBookStorePrice());
        Cart fakeCart = new FakeCart(testCatalog);
        fakeCart.add(productSellByBookStore(), 1);

        return fakeCart;
    }

    public MerchantProcessor getDummyMerchantProcessor(){
        return new DummyMerchantProcessor();
    }

    public CreditCard getCreditCardNotExpired(){
        return new CreditCard(validCreditCardNumber(), validCreditCardOwner(), YearMonth.from(LocalDate.now()));
    }

}
