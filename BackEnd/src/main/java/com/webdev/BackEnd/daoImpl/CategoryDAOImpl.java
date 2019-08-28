package com.webdev.BackEnd.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webdev.BackEnd.dao.CategoryDAO;
import com.webdev.BackEnd.dto.Category;


@Repository("tempCategoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {
	
	@Autowired 
	private SessionFactory sessionFactory;
	
	@Override
	public List<Category> list() {
		
		String selectActiveCategory = "FROM Category WHERE active=:active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		
		query.setParameter("active", true);
		
		return query.getResultList();
	}

	/*
	 * Getting a single category based on id
	 */
	@Override
	public Category get(int id) {
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override
	public boolean add(Category tempCategory) {
		
		try
		{
			//add the category to database table
			sessionFactory
				.getCurrentSession()
					.persist(tempCategory);
			return true;
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * Updating a single category
	 */
	@Override
	public boolean update(Category tempCategory) {
		try
		{
			//update the category 
			sessionFactory.getCurrentSession().update(tempCategory);
				
			return true;
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * Deleting a single category
	 */
	@Override
	public boolean delete(Category tempCategory) {
		
		tempCategory.setActive(false);
		
		try
		{
			//delete the category 
			sessionFactory.getCurrentSession().update(tempCategory);
				
			return true;
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
