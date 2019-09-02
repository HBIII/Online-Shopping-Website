package com.webdev.BackEnd.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.webdev.BackEnd.dao.UserDAO;
import com.webdev.BackEnd.dto.Address;
import com.webdev.BackEnd.dto.Cart;
import com.webdev.BackEnd.dto.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user;
	private Address address;
	private Cart cart;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.webdev.BackEnd");
		context.refresh();

		userDAO = (UserDAO) context.getBean("userDAO");
	}

	/*
	 * @Test public void testAdd() {
	 * 
	 * user = new User() ; user.setFirstName("Hrithik"); user.setLastName("Roshan");
	 * user.setEmail("hr@gmail.com"); user.setContactNumber("1234512345");
	 * user.setRole("USER"); user.setPassword("123456");
	 * 
	 * //add the user
	 * assertEquals("Failed to add user!",true,userDAO.addUser(user));
	 * 
	 * 
	 * address = new Address();
	 * address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
	 * address.setAddressLineTwo("Near Kaabil Store"); address.setCity("Mumbai");
	 * address.setState("Maharashtra"); address.setCountry("India");
	 * address.setPostalCode("400001"); address.setBilling(true);
	 * 
	 * //link user with address using user id address.setUserId(user.getId());
	 * 
	 * assertEquals("Failed to add address!",true,userDAO.addAddress(address));
	 * 
	 * if(user.getRole().equals("USER")) { //create a cart for this user cart=new
	 * Cart(); cart.setUser(user);
	 * 
	 * assertEquals("Failed to add Cart!",true,userDAO.addCart(cart));
	 * 
	 * //add shipping address to this user address = new Address();
	 * address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
	 * address.setAddressLineTwo("Near Kudrat Store"); address.setCity("Mumbai");
	 * address.setState("Maharashtra"); address.setCountry("India");
	 * address.setPostalCode("400001"); //set shipping to true
	 * address.setShipping(true);
	 * 
	 * //link it to user address.setUserId(user.getId());
	 * 
	 * assertEquals("Failed to add the shipping address!", true,
	 * userDAO.addAddress(address)); } }
	 */

	
	  @Test public void testAdd() {
	  
	  user = new User() ; user.setFirstName("Hrithik"); user.setLastName("Roshan");
	  user.setEmail("hr@gmail.com"); user.setContactNumber("1234512345");
	  user.setRole("USER"); user.setPassword("123456");
	  
	  if(user.getRole().equals("USER")) { 
		  //create a cart for this user 
		  cart=new Cart();
		  cart.setUser(user);
		  user.setCart(cart); 
	  }
	   
	 assertEquals("Failed to add user!",true,userDAO.add(user)); 
	}
	 

//	@Test
//	public void testUpdateCart() {
//
//		// fetch the user by it's email
//		user = userDAO.getByEmail("hr@gmail.com");
//
//		// get the cart of the user
//		cart = user.getCart();
//
//		cart.setGrandTotal(5555);
//		cart.setCartLines(1);
//
//		assertEquals("Failed to update the cart!", true, userDAO.updateCart(cart));
//	}
	
//	@Test
//	public void testAddAddress() {
//		 user = new User() ; 
//		 user.setFirstName("Hrithik"); 
//		 user.setLastName("Roshan");
//		 user.setEmail("hr@gmail.com"); 
//		 user.setContactNumber("1234512345");
//		 user.setRole("USER"); 
//		 user.setPassword("123456");
//		 
//		  //add the user
//		  assertEquals("Failed to add user!",true,userDAO.addUser(user));
//		  
//		  address = new Address();
//		  address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
//		  address.setAddressLineTwo("Near Kaabil Store");
//		  address.setCity("Mumbai");
//		  address.setState("Maharashtra"); 
//		  address.setCountry("India");
//		  address.setPostalCode("400001"); 
//		  address.setBilling(true);
//		  
//		  address.setUser(user);
//		  
//		  assertEquals("Failed to add address!", true, userDAO.addAddress(address));
//		  
//		  address = new Address();
//		  address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
//		  address.setAddressLineTwo("Near Kudrat Store"); 
//		  address.setCity("Mumbai");
//		  address.setState("Maharashtra"); 
//		  address.setCountry("India");
//		  address.setPostalCode("400001");
//		  address.setShipping(true);
//		  
//		  address.setUser(user);
//		  
//		  assertEquals("Failed to add shipping address!", true, userDAO.addAddress(address));
//	}
	
//	@Test
//	public void testAddAddress() {
//
//		  user = userDAO.getByEmail("hr@gmail.com");	
//		
//		  address = new Address();
//		  address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
//		  address.setAddressLineTwo("Near Kaabil Store");
//		  address.setCity("Banglore");
//		  address.setState("Karnataka"); 
//		  address.setCountry("India");
//		  address.setPostalCode("400001"); 
//		  address.setShipping(true);
//		  
//		  address.setUser(user);
//		  
//		  assertEquals("Failed to add address!", true, userDAO.addAddress(address));
//		  
//	}
	
//	@Test
//	public void testGetAddresses() {
//		user = userDAO.getByEmail("hr@gmail.com");
//		
//		assertEquals("Failed to fetch the list of addresses and size does not match!", 2, userDAO.listShippingAddresses(user).size());
//		
//		assertEquals("Failed to fetch the billing address and size does not match!", "Mumbai", userDAO.getBillingAddress(user).getCity());
//	}
}
