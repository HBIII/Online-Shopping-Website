package com.webdev.FrontEnd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.webdev.BackEnd.dao.CategoryDAO;
import com.webdev.BackEnd.dao.ProductDAO;
import com.webdev.BackEnd.dto.Category;
import com.webdev.BackEnd.dto.Product;
import com.webdev.FrontEnd.util.FileUploadUtility;
import com.webdev.FrontEnd.validator.ProductValidator;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO tempCategoryDAO;
	
	@Autowired
	private ProductDAO tempProductDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {
		
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("userClickManageProduct", true);
		mv.addObject("title", "Manage Products");
		
		Product nProduct = new Product();
		
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		
		mv.addObject("product",nProduct);
		
		if(operation!=null) {
			
			if(operation.equals("product")) {
				mv.addObject("message","Product submitted successfully!");
			}
			else if(operation.equals("category")) {
				mv.addObject("message","Category submitted successfully!");
			}
		}
		
		return mv;	
	}
	
	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {
		
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("userClickManageProduct", true);
		mv.addObject("title", "Manage Products");
		
		//fetch the product from the database
		Product nProduct = 	tempProductDAO.get(id);
		
		//set the product fetched from database 
		mv.addObject("product",nProduct);
		
		return mv;	
	}
	
	
	//handling product submission
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmssion(@Valid @ModelAttribute("product") Product mProduct, BindingResult results, Model model, HttpServletRequest request) {
		
		//handle image validation for new products
		if(mProduct.getId()==0) {
			new ProductValidator().validate(mProduct, results);
		}
		
		else {
			if(!mProduct.getFile().getOriginalFilename().equals(""))
			{
				new ProductValidator().validate(mProduct, results);
			}
		}
		
		//check if there are any errors
		if(results.hasErrors()) {
			model.addAttribute("userClickManageProduct",true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for Product Submission!!");
			
			return "page";
		}
		
		logger.info(mProduct.toString());
		
	
		if(mProduct.getId() == 0) {
			//create a new product if id is 0
			tempProductDAO.add(mProduct);
		}
		else {
			//update the product if id is not 0
			tempProductDAO.update(mProduct);
		}
		
		
		
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";
	}
	
	@RequestMapping(value = "/product/{id}/activation", method = RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		
		//is going to fetch the product from the database
		Product tempProduct = tempProductDAO.get(id);
		
		boolean isActive = tempProduct.isActive();
		
		//activating and deactivating based on the value of active field
		tempProduct.setActive(!tempProduct.isActive());
		
		//updating the product
		tempProductDAO.update(tempProduct);

		return (isActive)?
				"You have successfully deactivated the product with id: "+ tempProduct.getId() :
				"You have successfully activated the productwith id: "+ tempProduct.getId();
	}
	
	
	//handle category submission
	@RequestMapping(value = "/category" , method = RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
		
		//add the new category
		tempCategoryDAO.add(category);
		
		return "redirect:/manage/products/?operation=category";
	}
	
	//returning categories for all request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return tempCategoryDAO.list();
	}
	
	@ModelAttribute("category")
	public Category getCategory() {
		return new Category(); 
	}
	
	
}
