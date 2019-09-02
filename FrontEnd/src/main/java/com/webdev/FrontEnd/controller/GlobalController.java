package com.webdev.FrontEnd.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.webdev.BackEnd.dao.UserDAO;
import com.webdev.BackEnd.dto.User;
import com.webdev.FrontEnd.model.UserModel;

@ControllerAdvice
public class GlobalController {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private HttpSession session;
	
	private UserModel userModel = null;	
	
	@ModelAttribute("userModel")
	public UserModel getUserModel() {
		
		if(session.getAttribute("userModel")==null) {
			//add the user model
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			User user = userDAO.getByEmail(authentication.getName());
			
			if(user!=null) {
				// create a new userModel object to pass the user details
				userModel = new UserModel();
				// set the name and the id
				userModel.setId(user.getId());
				userModel.setFullName(user.getFirstName() + " " + user.getLastName());
				userModel.setRole(user.getRole());
				
				if(user.getRole().equals("USER")) {
					//set the cart only if the user is buyer
					userModel.setCart(user.getCart());					
				}				

				//set the userModel in session
				session.setAttribute("userModel", userModel);
				return userModel;
			}
			
		}
		
		return (UserModel) session.getAttribute("userModel");	
	}
}
