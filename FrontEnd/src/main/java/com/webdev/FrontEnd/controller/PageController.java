package com.webdev.FrontEnd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.webdev.BackEnd.dao.CategoryDAO;
import com.webdev.BackEnd.dao.ProductDAO;
import com.webdev.BackEnd.dto.Category;
import com.webdev.BackEnd.dto.Product;
import com.webdev.FrontEnd.exception.ProductNotFoundException;

@Controller
public class PageController {
	
		private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
		@Autowired
		private CategoryDAO tempCategoryDAO;
		
		@Autowired
		private ProductDAO tempProductDAO;

		@RequestMapping(value = {"/", "/home", "/index"})
		public ModelAndView index() {
			
			ModelAndView mv= new ModelAndView("page");
			mv.addObject("title", "Home");
			
			logger.info("inside PageController index method - INFO");
			logger.debug("inside PageController index method - DEBUG");
			
			//passing the list of categories
			mv.addObject("categories", tempCategoryDAO.list());
			
			mv.addObject("userClickHome", "true");
			return mv;
		}
		
		@RequestMapping(value = "/about")
		public ModelAndView about() {
			
			ModelAndView mv= new ModelAndView("page");
			mv.addObject("title", "About Us");
			mv.addObject("userClickAbout", "true");
			return mv;
		}
		
		@RequestMapping(value = "/contact")
		public ModelAndView contact() {
			
			ModelAndView mv= new ModelAndView("page");
			mv.addObject("title", "Contact Us");
			mv.addObject("userClickContact", "true");
			return mv;
		}
		
		//methods to load all the products
		
		@RequestMapping(value = "/show/all/products")
		public ModelAndView showAllProducts() {
			
			ModelAndView mv= new ModelAndView("page");
			mv.addObject("title", "All Products");
			
			//passing the list of categories
			mv.addObject("categories", tempCategoryDAO.list());
			
			mv.addObject("userClickAllProducts", "true");
			return mv;
		}
		
		@RequestMapping(value = "/show/category/{id}/products")
		public ModelAndView showCategoryProducts(@PathVariable ("id") int id) {
			
			ModelAndView mv= new ModelAndView("page");
			
			Category tempCategory=null;
			
			tempCategory = tempCategoryDAO.get(id);
			
			mv.addObject("title", tempCategory.getName());
			
			//passing the list of categories
			mv.addObject("categories", tempCategoryDAO.list());
			
			mv.addObject("category", tempCategory);
			
			mv.addObject("userClickCategoryProduct", "true");
			return mv;
		}
		/*
		 * View single product
		 */
		@RequestMapping(value = "/show/{id}/product")
		public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException {
			
			ModelAndView mv = new ModelAndView("page");
			
			Product tempProduct = tempProductDAO.get(id);
			
			if(tempProduct==null) throw new ProductNotFoundException();
			
			//update views
			tempProduct.setViews(tempProduct.getViews()+1);
			
			tempProductDAO.update(tempProduct);
			
			mv.addObject("title", tempProduct.getName());
			
			mv.addObject("product", tempProduct);
			
			mv.addObject("userClickShowProduct", "true");
			
			return mv;
		}
		
		
}
