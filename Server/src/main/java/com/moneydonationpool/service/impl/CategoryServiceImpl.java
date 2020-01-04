package com.moneydonationpool.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneydonationpool.dao.CategoryDao;
import com.moneydonationpool.entity.CategoryEntity;
import com.moneydonationpool.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryDao categoryDao; 

	@Override
	public List<CategoryEntity> getAllCategories() {
		List<CategoryEntity> AllCategoriesList = categoryDao.getAllCategories();
		return AllCategoriesList;
	}
	

}
