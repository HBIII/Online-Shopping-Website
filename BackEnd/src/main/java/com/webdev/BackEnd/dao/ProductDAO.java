package com.webdev.BackEnd.dao;

import java.util.List;

import com.webdev.BackEnd.dto.Product;

public interface ProductDAO {
	
	Product get(int productId);
	List<Product> list();
	boolean add(Product tempProduct);
	boolean update(Product tempProduct);
	boolean delete(Product tempProduct);
	
	//business logic 
	List<Product> listActiveProducts();
	List<Product> listActiveProductsByCategory(int categoryId);
	List<Product> getLatestActiveProducts(int count);
	
}
