package com.tests.example.doubles;

import com.tests.example.business.merchantProcessors.MerchantProcessor;
import com.tests.example.model.CreditCard;

public class DummyMerchantProcessor implements MerchantProcessor {

    @Override
    public void debit(CreditCard creditCard, double amount) {
    }
}
