package com.tests.example.business;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    public static final String INVALID_USER = "The user is invalid";
    public static final String PRODUCT_IS_NOT_SELL_BY_SUPERMARKET = "Product is not sell by supermarket";
    public static final String PRODUCT_QUANTITY_MUST_BE_STRICTLY_POSITIVE = "Product quantity must be strictly positive";
   
    private Map<Object,Double> catalog;
    private Map<Object, Long> products = new HashMap<>();
    protected Boolean validUser;

    public Cart(Map<Object,Double> catalog){
        this.validUser = false;
        this.catalog = catalog;
    }

    public void add(Object aProduct) {
        add(aProduct,1);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public boolean contains(Object aProduct) {
        return products.containsKey(aProduct);
    }

    public void add(Object aProduct, int aQuantity) {
        assertValidUser();
        assertQuantityIsStrictlyPositive(aQuantity);
        assertProductIsSellBySupermarket(aProduct);
        if(products.containsKey(aProduct)){
            products.put(aProduct, products.get(aProduct) + (long) aQuantity);
        }else{
            products.put(aProduct, (long) aQuantity);
        }
    }


    public void assertProductIsSellBySupermarket(Object aProduct) {
        if(!catalog.containsKey(aProduct)) throw new RuntimeException(PRODUCT_IS_NOT_SELL_BY_SUPERMARKET);
    }

    public void assertQuantityIsStrictlyPositive(int aQuantity) {
        if (aQuantity<1) throw new RuntimeException(PRODUCT_QUANTITY_MUST_BE_STRICTLY_POSITIVE);
    }

    public void assertValidUser() {
        if (!validUser) throw new RuntimeException(INVALID_USER);
    }

    public double total() {

            DecimalFormat df = new DecimalFormat("0.00");
            double unformattedCartTotal = products.keySet().stream().
                    mapToDouble(addedProduct -> priceOf(addedProduct) * products.get(addedProduct)).
                    reduce(0.0, Double::sum);
            return unformattedCartTotal;

    }

    private Double priceOf(Object aProduct) {
        return catalog.get(aProduct);
    }

    public Map<Object, Long> getProducts() {
        return products;
    }

    public Boolean getValidUser() {
        return validUser;
    }

    public void setValidUser(Boolean validUser) {
        this.validUser = validUser;
    }
}
