package com.webdev.BackEnd.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.webdev.BackEnd.dao.CategoryDAO;
import com.webdev.BackEnd.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDAO tempCategoryDAO;
	
	private Category tempCategory;
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext();
		context.scan("com.webdev.BackEnd");
		context.refresh();
		
		tempCategoryDAO = (CategoryDAO) context.getBean("tempCategoryDAO");
		
	}
	
	/*
	@Test
	public void testAddCategory() {
		
		tempCategory = new Category();
		
		tempCategory.setName("Television");
		tempCategory.setDescription("Some Description");
		tempCategory.setImageUrl("CAT_1.png");
		
		assertEquals("Successfully added  a category inside the table!", true,tempCategoryDAO.add(tempCategory));
	}
	*/
	
	/*
	@Test
	public void testGetCategory() {
		
		tempCategory = tempCategoryDAO.get(1);
		
		assertEquals("Successfully fetched a category from the table!", "Television",	tempCategory.getName());
	}
	*/
	
	/*
	@Test
	public void testUpdateCategory() {
		
		tempCategory = tempCategoryDAO.get(1);
		
		tempCategory.setName("TV");
		
		assertEquals("Successfully updated a category from the table!", true, tempCategoryDAO.update(tempCategory));
	}
	*/
	
	/*
	@Test
	public void testDeleteCategory() {
		
		tempCategory = tempCategoryDAO.get(1);
		
		assertEquals("Successfully deleted a category from the table!", true, tempCategoryDAO.delete(tempCategory));
	}
	*/
	
	/*
	@Test
	public void testListCategory() {
		
		assertEquals("Successfully fetched list of categories from the table!", 1, tempCategoryDAO.list().size());
	}
	*/
	
	@Test
	public void testCRUDCategory() {
		
		
		//adding the category
		tempCategory = new Category();
		
		tempCategory.setName("Laptop");
		tempCategory.setDescription("Some Description");
		tempCategory.setImageUrl("CAT_2.png");
		
		assertEquals("Successfully added  a category inside the table!", true,tempCategoryDAO.add(tempCategory));
		
		tempCategory = new Category();
		
		tempCategory.setName("Mobile");
		tempCategory.setDescription("Some Description");
		tempCategory.setImageUrl("CAT_2.png");
		
		assertEquals("Successfully added  a category inside the table!", true,tempCategoryDAO.add(tempCategory));
		
		
		//fetching and renaming  the category	
		tempCategory = tempCategoryDAO.get(2);
		
		tempCategory.setName("PC");
		
		assertEquals("Successfully fetched and updated a category from the table!", true,tempCategoryDAO.update(tempCategory));
		
		//deleting the category
		assertEquals("Successfully deleted a category from the table!", true, tempCategoryDAO.delete(tempCategory));
		
		//fetching the list 	
		assertEquals("Successfully fetched list of categories from the table!", 1, tempCategoryDAO.list().size());
	}
	
	
}
