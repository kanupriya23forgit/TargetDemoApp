package com.example.myRetail.service.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.myRetail.dto.Product;
import com.example.myRetail.dto.ProductDTO;
import com.example.myRetail.exception.ProductNotFoundException;
import com.example.myRetail.repository.ProductRepository;
import com.example.myRetail.service.ProductService;

/**
 * This is the service class which handles different operations like get/ create and update on the product entity . 
 * 
 * @author kanupriya
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Qualifier("ProductRepository")
	@Autowired
	private ProductRepository repository;

	/**
	 * 
	 */
	@Override
	public ProductDTO getProductDetails(int productId) throws ProductNotFoundException {
		ProductDTO productInfo = new ProductDTO();
		String id = String.valueOf(productId) ;
		productInfo.setName(repository.getProductName(id));
		productInfo.setId(id);
		
		CompletableFuture.allOf(CompletableFuture.supplyAsync(() -> {
			return repository.getProductName(id);
		}).thenAccept(name-> productInfo.setName(name)),CompletableFuture.supplyAsync(() -> {
			return repository.getPrice(id);
		}).thenAccept(price-> productInfo.setPrice(price))).join();
		
		return productInfo;

	}

	@Override
	public int addProduct(Product productInfo) {
		return repository.addProduct(productInfo);
	}

	@Override
	public void updateProduct(String id, Product productInfo) {
		repository.updateProduct(id, productInfo); 

	}

}
