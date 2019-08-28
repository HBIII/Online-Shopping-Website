package com.webdev.BackEnd.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webdev.BackEnd.dao.ProductDAO;
import com.webdev.BackEnd.dto.Product;


@Repository("tempProductDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Product get(int productId) {
		try
		{
			return sessionFactory
					.getCurrentSession()
						.get(Product.class, Integer.valueOf(productId));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> list() {
		return sessionFactory
				.getCurrentSession()
					.createQuery("FROM Product", Product.class)
						.getResultList();
	}

	@Override	
	public boolean add(Product tempProduct) {
		try
		{
			 sessionFactory
					.getCurrentSession()
						.persist(tempProduct);
			 return true;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean update(Product tempProduct) {
		try
		{
			//update the category 
			sessionFactory
				.getCurrentSession()
					.update(tempProduct);
			return true;
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean delete(Product tempProduct) {
		
		try
		{
			tempProduct.setActive(false);
			//delete the Product
			
			return this.update(tempProduct);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts = "FROM Product WHERE active = :active";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectActiveProducts, Product.class)
						.setParameter("active", true)
							.getResultList();
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String selectActiveProductsByCategory = "FROM Product WHERE active = :active AND categoryId = :categoryId";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectActiveProductsByCategory, Product.class)
						.setParameter("active", true)
							.setParameter("categoryId", categoryId)
								.getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		return sessionFactory
				.getCurrentSession()
					.createQuery("FROM Product where active = :active ORDER BY id", Product.class)
						.setParameter("active", true)
							.setFirstResult(0)
								.setMaxResults(count)
									.getResultList();
	}

}
