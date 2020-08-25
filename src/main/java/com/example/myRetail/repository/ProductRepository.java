package com.example.myRetail.repository;
/**
 * 
 */

import com.example.myRetail.dto.Price;
import com.example.myRetail.dto.Product;
import com.example.myRetail.exception.ProductNotFoundException;

/**
 * @author kanupriya
 *
 */
public interface ProductRepository  {
	
	String getProductName (String productId) throws ProductNotFoundException;
	
	Price getPrice (String productId) throws ProductNotFoundException ;
	
	int addProduct (Product productInfo)  ;
	
	boolean updateProduct ( String productId , Product productInfo ) throws ProductNotFoundException  ;

}
