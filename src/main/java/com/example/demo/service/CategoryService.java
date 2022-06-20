package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;



@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	public void AddCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public List<Category> getAllCategory(){
		return categoryRepository.findAll();
	}
	
	public void deleteCategory(int id) {
		categoryRepository.deleteById(id);
	}

	public Optional<Category> getCategoryById(int id) {
		return categoryRepository.findById(id);
	}
}
