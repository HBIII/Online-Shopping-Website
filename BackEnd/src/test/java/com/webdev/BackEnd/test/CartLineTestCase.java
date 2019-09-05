package com.webdev.BackEnd.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.webdev.BackEnd.dao.CartLineDAO;
import com.webdev.BackEnd.dao.ProductDAO;
import com.webdev.BackEnd.dao.UserDAO;
import com.webdev.BackEnd.dto.Cart;
import com.webdev.BackEnd.dto.CartLine;
import com.webdev.BackEnd.dto.Product;
import com.webdev.BackEnd.dto.User;

public class CartLineTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CartLineDAO cartLineDAO = null;
	private static ProductDAO productDAO = null;
	private static UserDAO userDAO = null;
	
	private Product product = null;
	private User user = null;
	private Cart cart = null;
	private CartLine cartLine = null;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.webdev.BackEnd");
		context.refresh();
		cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");
		productDAO = (ProductDAO)context.getBean("tempProductDAO");
		userDAO = (UserDAO)context.getBean("userDAO");
	}
		
	@Test
	public void testAddCartLine() {
		
		// fetch the user and then cart of that user
		User user = userDAO.getByEmail("sania@gmail.com");	
		
		Cart cart = user.getCart();
		
		// fetch the product 
		Product product = productDAO.get(1);
		
		// Create a new CartLine
		cartLine = new CartLine();
		
		cartLine.setBuyingPrice(product.getUnitPrice());
		
		cartLine.setProductCount(cartLine.getProductCount()+1);
		
		cartLine.setTotal(product.getUnitPrice() * cartLine.getProductCount());
		
		cartLine.setAvailable(true);
		
		cartLine.setCartId(cart.getId());
		
		cartLine.setProduct(product);
		
		//cart.setGrandTotal(cart.getGrandTotal() + (cartLine.getTotal() - oldTotal));
		
		assertEquals("Failed to add the CartLine!",true, cartLineDAO.add(cartLine));	
		
		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		
		cart.setCartLines(cart.getCartLines() + 1);
		
		assertEquals("Failed to update the cart!",true, cartLineDAO.updateCart(cart));
	}

}
