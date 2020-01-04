package com.moneydonationpool.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneydonationpool.entity.CategoryEntity;
import com.moneydonationpool.service.CategoryService;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
    CategoryService categoryService; 
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	@GetMapping("/")
	public List<CategoryEntity> getAllCategories() {
		LOGGER.info("getAllCategories servvice called");
		return categoryService.getAllCategories();
	}

}
