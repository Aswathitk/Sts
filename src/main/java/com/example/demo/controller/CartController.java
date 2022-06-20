package com.example.demo.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CartDTO;
import com.example.demo.global.ApiResponse;
import com.example.demo.global.GlobalData;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.service.CartService;
import com.example.demo.service.CustomUserDetailService;
import com.example.demo.service.ProductService;

@RestController
public class CartController{

	@Autowired
	ProductService productservice;
	
	@Autowired
	CustomUserDetailService userService;
	
	@Autowired
	CartService cartService;
	
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id,Principal principal) {
//	public ResponseEntity<ApiResponse> addToCart(@PathVariable int id,Principal principal) {

		LocalDateTime t= LocalDateTime.now();
		Cart cart=new Cart();
		cart.setCreatedDate(t);
		cart.setProduct(productservice.getProductById(id).get());
		cart.setUser(userService.getUserByEmail(principal.getName()));
		cartService.addToCart(cart);
//		return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
		return "shop";
	}
	
	@GetMapping("/cart")
	public List<Product> getCart(Model model,Principal principal) {
		User id = userService.getUserByEmail(principal.getName());
		List<Cart> item_list=cartService.getCartByUser(id);
		List<Product> products = new ArrayList<>();
		Iterator<Cart> iterator = item_list.iterator();
	      while(iterator.hasNext()) {
	         products.add(iterator.next().getProduct());
	      }
	      System.out.println("products = "+products);
	      List<Product> prodts=products;
//		model.addAttribute("cart",prodts);
		return prodts;
	}
	
	@GetMapping("/cart/removeItem/{index}")
	public String removeFromCart(@PathVariable int index) {
		cartService.remove(index);
		return "redirect:/cart";
		   
	}
	
	
	
//	@GetMapping("/checkout")
//	public String checkout(Model model) {
//		model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
//		return "checkout";
//	}
}
