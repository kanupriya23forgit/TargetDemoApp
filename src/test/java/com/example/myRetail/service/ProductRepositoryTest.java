package com.example.myRetail.service;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.myRetail.dto.Product;
import com.example.myRetail.exception.ProductNotFoundException;
import com.example.myRetail.repository.impl.ProductRepositoryImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductRepositoryTest {
	
	@Mock
	JdbcTemplate jdbcTemplate;
	
	/*
	 * @Mock KeyHolder keyHolder ;
	 */
	
	@InjectMocks
	ProductRepositoryImpl productRepository;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	//@Test
	public void addProductTest() throws SQLException {
		
		String sql = "INSERT INTO PRODUCT(name,price,currency) VALUES(?,?,?)";
		Product productInfo = new Product();
		productInfo.setName("XYZ");
		productInfo.setPrice("123");
		productInfo.setCurrency("USD");
		
		PreparedStatementCreator ps = Mockito.mock(PreparedStatementCreator.class);
		KeyHolder keyHolder  = Mockito.mock(KeyHolder.class);
		Mockito.when(keyHolder.getKey()).thenReturn(1);
		
		
		Mockito.when(jdbcTemplate.update(ps,keyHolder)).thenReturn(1);
					
		int productId = productRepository.addProduct(productInfo) ;
		Assert.assertEquals(1, productId);
		
	}
	
	@Test
	public void updateProductTest() throws SQLException {
		
		String sql = "UPDATE PRODUCT SET NAME = ? , PRICE = ? ,CURRENCY = ? WHERE ID = ? ";
		Product productInfo = new Product();
		productInfo.setName("XYZ");
		productInfo.setPrice("123");
		productInfo.setCurrency("USD");
		
		Mockito.when(jdbcTemplate.update(sql, productInfo.getName(), productInfo.getPrice(),
				productInfo.getCurrency(), "1")).thenReturn(1);
		
		Assert.assertEquals(true, productRepository.updateProduct("1" ,productInfo));
		
	}
	
	@Test()
	public void updateProductTestWithException() throws SQLException {
		
		Product productInfo = new Product();
		productInfo.setName("XYZ");
		productInfo.setPrice("123");
		productInfo.setCurrency("USD");
		
		Exception exception = assertThrows(ProductNotFoundException.class, () -> {
			productRepository.updateProduct("10", productInfo);
	    });
	    
	    Assert.assertTrue( exception.getClass().getTypeName().contains("ProductNotFoundException"))  ;
		
		
	}
	

}
