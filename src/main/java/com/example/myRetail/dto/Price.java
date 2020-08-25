package com.example.myRetail.dto;

public class Price {
	
	private String currentPrice; 
	
	private String currency ;

	public Price(String currentPrice, String currency) {
		super();
		this.currentPrice = currentPrice;
		this.currency = currency;
	} 
	
	public String getCurrentPrice() {
		return currentPrice;
	}

	public String getCurrency() {
		return currency;
	}

}
