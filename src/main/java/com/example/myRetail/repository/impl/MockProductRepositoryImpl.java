package com.example.myRetail.repository.impl;

import org.springframework.stereotype.Repository;

import com.example.myRetail.dto.Price;
import com.example.myRetail.dto.Product;
import com.example.myRetail.exception.ProductNotFoundException;
import com.example.myRetail.repository.ProductRepository;

@Repository("MockProductRepository")
public class MockProductRepositoryImpl implements ProductRepository {

	@Override
	public String getProductName(String productId) {
		switch (productId) {
		case "146871":
			return "RedMI Note 3";
		case "189723":
			return "Iphone 8";
		case "76767":
			return "One Plus 6";
		}
		return null;
	}

	@Override
	public Price getPrice(String productId) {
		Price price = null;
		switch (productId) {
		case "146871":
			price  = new Price("CNY", "1536.22") ;
			break ;
		case "189723":
			price  = new Price("CNY", "1536.22") ;
			break ;
		case "76767":
			price  = new Price("CNY", "1536.22") ;
			break ;
		}
		return price;
	}

	@Override
	public int addProduct(Product productInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateProduct(String productId, Product productInfo) throws ProductNotFoundException {
		return false;
		// TODO Auto-generated method stub
		
	}

}
