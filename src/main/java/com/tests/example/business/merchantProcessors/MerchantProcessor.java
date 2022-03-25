package com.tests.example.business.merchantProcessors;

import com.tests.example.model.CreditCard;

public interface MerchantProcessor {
    String CREDIT_CARD_STOLEN = "Credit card stolen";
    String CREDIT_CARD_WITHOUT_CREDIT = "Credit card without credit";

     void debit(CreditCard creditCard, double amount);
}
