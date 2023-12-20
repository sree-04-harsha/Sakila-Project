package com.cg.sakila.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sakila.enitites.Category;
import com.cg.sakila.repositories.CategoryRepository;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	
}
