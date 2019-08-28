package com.webdev.BackEnd.dao;

import java.util.List;

import com.webdev.BackEnd.dto.Category;

public interface CategoryDAO {
	
	List<Category> list();
	
	Category get(int id);
	
	boolean add(Category tempCategory);
	boolean update(Category tempCategory);
	boolean delete(Category tempCategory);
}
