/**
 * 
 */
package com.example.myRetail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myRetail.dto.Product;
import com.example.myRetail.dto.ProductDTO;
import com.example.myRetail.exception.ProductNotFoundException;
import com.example.myRetail.service.ProductService;

/**
 * @author kanupriya
 *
 */
@RestController
@RequestMapping(path = "/products")
public class ProductDetailsController {

	@Autowired
	private ProductService productService;

	/**This method which returns the product details on the basis of input product id.
	 * Exception will be thrown if the product does not exist.
	 * 
	 * @param id
	 * @return ResponseEntity<Product>
	 */
	@GetMapping(path = "/{id}")
	@ResponseBody
	public ResponseEntity<Product> getDetails(@PathVariable("id") int id) {
		ProductDTO productInfo = null;
		productInfo = productService.getProductDetails(id);
		Product productDetails = new Product();
		productDetails.setId(productInfo.getId());
		productDetails.setName(productInfo.getName());
		productDetails.setPrice(productInfo.getPrice().getCurrentPrice());
		productDetails.setCurrency(productInfo.getPrice().getCurrency());
		return new ResponseEntity<>(productDetails, HttpStatus.OK);
	}

	/**This api will create a new product and returns a unique productId.
	 * 
	 * Exception will be thrown in case a product with the same name already exists.
	 * 
	 * @param id
	 * @return ResponseEntity
	 */
	@PostMapping
	public ResponseEntity addProduct(@RequestBody Product product) {
		try {
			int count = productService.addProduct(product);
			return new ResponseEntity<>(count, HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			throw e;
		}
	}

	/**
	 * This api is used to update the product details of a given productId.
	 * Exception will be thrown if the product does not exist.
	 * 
	 * @param id
	 * @param product
	 * @return ResponseEntity
	 */
	@PutMapping(path = "/{id}")
	public ResponseEntity updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
		productService.updateProduct(id, product);
		return new ResponseEntity<>(HttpStatus.OK);

	}

}
