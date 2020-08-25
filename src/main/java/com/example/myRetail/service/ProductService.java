/**
 * 
 */
package com.example.myRetail.service;

import com.example.myRetail.dto.Product;
import com.example.myRetail.dto.ProductDTO;

/**
 * @author kanupriya
 *
 */
public interface ProductService {

	ProductDTO getProductDetails(int id);

	int addProduct(Product productInfo);

	void updateProduct(String id, Product productInfo);

 
}
