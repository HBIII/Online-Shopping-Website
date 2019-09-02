package com.webdev.FrontEnd.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.webdev.BackEnd.dto.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Product tempProduct = (Product) target;
		
		//whether file has been selected or not
		if(tempProduct.getFile() == null || 
				tempProduct.getFile().getOriginalFilename().equals("")) {
			errors.rejectValue("file", null, "Please select an image file to upload");
			return;
		}
		
		if(! (tempProduct.getFile().getContentType().equals("image/jpeg") ||
			 tempProduct.getFile().getContentType().equals("image/png") ||
			 tempProduct.getFile().getContentType().equals("image/gif")
				)) {
			errors.rejectValue("file", null, "Please use only image file to upload");
			return;
		}

	}

}
