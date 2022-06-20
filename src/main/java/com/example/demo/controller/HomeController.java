package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.global.GlobalData;
import com.example.demo.service.CategoryService;
import com.example.demo.service.CustomUserDetailService;
import com.example.demo.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CustomUserDetailService customUserService;
	
//	@GetMapping("/")
//	public String home(Model model) {
//		model.addAttribute("cartCount",GlobalData.cart.size());
//		return "index";
//	}
	
	@GetMapping({"/shop","/"})
	public String getShop(Model model,Principal principal) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("products",productService.getAllProducts());
		return "shop";
	}
	
	@GetMapping("/shop/category/{id}")
	public String getShopByCategory(Model model, @PathVariable int id) {
		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("products",productService.getAllProductsByCategoryId(id));
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "shop";
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String getviewproduct(Model model, @PathVariable int id) {
		model.addAttribute("product",productService.getProductById(id).get());
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "viewProduct";
	}
	
}
