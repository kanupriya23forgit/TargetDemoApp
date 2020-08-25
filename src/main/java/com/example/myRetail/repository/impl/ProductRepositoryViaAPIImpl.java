/**
 * 
 */
package com.example.myRetail.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.myRetail.dto.Price;
import com.example.myRetail.dto.Product;
import com.example.myRetail.exception.ProductNotFoundException;
import com.example.myRetail.repository.ProductRepository;

/**
 * This class acts as the repository of product information by calling the third party apis for product name and price information.
 * @author kanupriya
 *
 */
@Repository("ProductRepositoryViaAPI")
public class ProductRepositoryViaAPIImpl implements ProductRepository {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String getProductName(String productId) {
		String productName = null;
		try {
			productName = restTemplate.getForObject("http://redsky.target.com/v2/pdp/tcin/" + productId
					+ "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics",
					String.class);
		} catch (Exception e) {
			throw new ProductNotFoundException(e) ;
		}
		return productName;
	}

	@Override
	public Price getPrice(String productId) {
		Price priceInfo = restTemplate
				.getForObject("https://price. myRetail.com/product/" + productId + "/price", Price.class);
	return priceInfo;
		
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
