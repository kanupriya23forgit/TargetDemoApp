package com.example.myRetail.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.myRetail.dto.Price;
import com.example.myRetail.dto.Product;
import com.example.myRetail.exception.ProductAlreadyExistsException;
import com.example.myRetail.exception.ProductNotFoundException;
import com.example.myRetail.repository.ProductRepository;

/**
 * This class is the repository which holds the product details . It interacts
 * with the data store to get/create and update the product schema.
 * 
 * @author kanupriya
 *
 */
@Repository("ProductRepository")
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public String getProductName(String productId) throws ProductNotFoundException {
		return getProductById(productId).getName();
	}

	@Override
	public Price getPrice(String productId) throws ProductNotFoundException {
		final Product product = getProductById(productId);
		final Price price = new Price(product.getPrice(), product.getCurrency());
		return price;
	}

	/**
	 * @param productId
	 * @return
	 */
	private Product getProductById(String productId) throws ProductNotFoundException {
		try {
			final Product product = jdbcTemplate.queryForObject("select * from product where id=" + productId,
					new ProductRowMapper());
			return product;
		} catch (EmptyResultDataAccessException e) {
			throw new ProductNotFoundException("Product with id [ " + productId + " ] not found");
		}
	}

	@Override
	public int addProduct(Product productInfo) {

		String INSERT_SQL = "INSERT INTO PRODUCT(name,price,currency) VALUES(?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
					ps.setString(1, productInfo.getName());
					ps.setString(2, productInfo.getPrice());
					ps.setString(3, productInfo.getCurrency());
					return ps;
				}
			}, keyHolder);
		} catch (DuplicateKeyException e) {
			throw new ProductAlreadyExistsException(
					"Product with name [ " + productInfo.getName() + " ] already exists ");
		}

		return keyHolder.getKey().intValue();
	}

	@Override
	public boolean updateProduct(String productId, Product productInfo) throws ProductNotFoundException {
		String sql = "UPDATE PRODUCT SET NAME = ? , PRICE = ? ,CURRENCY = ? WHERE ID = ? ";
		boolean updated = false;
		final int count = jdbcTemplate.update(sql, productInfo.getName(), productInfo.getPrice(),
				productInfo.getCurrency(), productId);
		updated = true;

		if (count <= 0) {
			throw new ProductNotFoundException("Product with id [ " + productId + " ] not found");
		}
		return updated;

	}

}

/**
 * 
 * Custom row mapper created to map the fields returned from the result set to
 * the product type fields *
 */
class ProductRowMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

		final Product product = new Product();
		product.setId(String.valueOf(rs.getInt("id")));
		product.setName(rs.getString("name"));
		product.setPrice(rs.getString("price"));
		product.setCurrency(rs.getString("currency"));
		return product;
	}

}
