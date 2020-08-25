package com.example.myRetail.dto;

public class Product {
	
	private String id;
	
	private String name;
	
	private String price;
	
	private String currency;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currencyCode) {
		this.currency = currencyCode;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", currencyCode=" + currency + "]";
	}
	
	

}
