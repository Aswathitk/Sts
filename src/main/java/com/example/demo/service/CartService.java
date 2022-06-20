package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cart;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.UserRepository;

@Service
public class CartService {

	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public void addToCart(Cart cart) {
		cartRepository.save(cart);
	}

	public List<Cart> getCartByUser(User id) {
		List<Cart> cart = cartRepository.findByUser(id);
		return cartRepository.findByUser(id);
	}

	public void remove(int index) {
		
		cartRepository.deleteById(index);
	}
}
