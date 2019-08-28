package com.webdev.BackEnd.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.webdev.BackEnd.dao.ProductDAO;
import com.webdev.BackEnd.dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;
	
	private static ProductDAO tempProductDAO;
	
	private Product tempProduct;
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext();
		context.scan("com.webdev.BackEnd");
		context.refresh();
		
		tempProductDAO = (ProductDAO) context.getBean("tempProductDAO");
		
	}
	/*
	@Test
	public void testCRUDCategory() {
		
		
		//adding the category
		tempProduct = new Product();
		
		tempProduct.setName("Oppo Selfie S53");
		tempProduct.setBrand("Oppo");
		tempProduct.setDescription("Some Description");
		tempProduct.setUnitPrice(25000);
		tempProduct.setActive(true);
		tempProduct.setCategoryId(3);
		tempProduct.setSupplierId(3);
		
		
		assertEquals("Something went wrong while inserting a new product!", true,tempProductDAO.add(tempProduct));
		
		
		//fetching and renaming  the category	
		tempProduct = tempProductDAO.get(2);
		
		tempProduct.setName("Samsung Galaxy S7");
		
		assertEquals("Something went wrong while updating an existing product!", true,tempProductDAO.update(tempProduct));
		
		//deleting the category
		assertEquals("Something went wrong while deleting the existing product!", true, tempProductDAO.delete(tempProduct));
		
		//fetching the list 	
		assertEquals("Something went wrong while fetching the list of products!", 6, tempProductDAO.list().size());
	}
	*/
	
	@Test
	public void testListActiveProducts() {
		assertEquals("Something went wrong while fetching the list of products!", 5, tempProductDAO.listActiveProducts().size());
	}
	
	@Test
	public void testListActiveProductsByCategoy() {
		assertEquals("Something went wrong while fetching the list of products!", 3, tempProductDAO.listActiveProductsByCategory(3).size());
		assertEquals("Something went wrong while fetching the list of products!", 2, tempProductDAO.listActiveProductsByCategory(1).size());
	}
	
	@Test
	public void testGetLatestActiveProduct() {
		assertEquals("Something went wrong while fetching the list of products!", 3, tempProductDAO.getLatestActiveProducts(3).size());
	}
}
