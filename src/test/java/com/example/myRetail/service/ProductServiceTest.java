package com.example.myRetail.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.myRetail.dto.Price;
import com.example.myRetail.dto.Product;
import com.example.myRetail.dto.ProductDTO;
import com.example.myRetail.repository.ProductRepository;
import com.example.myRetail.service.impl.ProductServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTest {
	
	@Mock
	ProductRepository productRepository ;
	
	@InjectMocks
	ProductServiceImpl productService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getProductDetailsTest() {
		
		String productId = "1";
		Mockito.when(productRepository.getProductName(productId)).thenReturn("XYZ");
		
		Price price = new Price("172", "USD");
		Mockito.when(productRepository.getPrice(productId)).thenReturn(price);
		
		//ProductService productService = new ProductServiceImpl();
		ProductDTO productDTO = productService.getProductDetails(Integer.valueOf(productId));
		
		Assert.assertEquals("XYZ", productDTO.getName());
		Assert.assertEquals(price, productDTO.getPrice());
		
	}
	
	@Test
	public void addProductTest() {
		final Product productInfo = new Product();
		productInfo.setId("2");
		productInfo.setName("TestProduct");
		productInfo.setPrice("200");
		productInfo.setCurrency("INR");
		
		Mockito.when(productRepository.addProduct(productInfo)).thenReturn(2);
		
		int productId = productService.addProduct(productInfo);
		
		Assert.assertEquals(2, productId);
	}
	
	
}
