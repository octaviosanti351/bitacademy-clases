package com.tests.example.model;

import com.tests.example.business.Cashier;

import java.util.Map;

public class Sale {

	private Object number;
	private Cashier cashier;
	private double total;

	public Sale(Object aNumber, Cashier aCashier, double aTotal) {
		this.number = aNumber;
		this.cashier = aCashier;
		this.total = aTotal;
	}

	public boolean isOf(Object aClient) {
		return cashier.isClient(aClient);
	}

	public double total() {
		return total;
	}


	public Object number() {
		return number;
	}

}
