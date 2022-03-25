package com.tests.example.model;

import java.time.LocalDate;
import java.time.YearMonth;

public class CreditCard {
    private YearMonth expirationDate;
    private String owner;
    private String number;

    public CreditCard(String number, String owner, YearMonth expirationDate) {
        this.number = number;
        this.owner = owner;
        this.expirationDate = expirationDate;
    }

    public boolean isExpiredOn(LocalDate aDate) {
        return expirationDate.isBefore(YearMonth.from(aDate));
    }

    public String number() {
        return number;
    }

    public YearMonth expirationDate() {
        return expirationDate;
    }

    public String owner() {
        return owner;
    }
}
