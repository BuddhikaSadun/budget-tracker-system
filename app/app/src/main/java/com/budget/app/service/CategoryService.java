package com.budget.app.service;

import com.budget.app.entity.Category;
import com.budget.app.respository.CategoryRepository;


public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService( CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

     public Category deleteCategory(Category category){
        return categoryRepository.deleteById(category.setCategoryId(null));
    }
    
}
