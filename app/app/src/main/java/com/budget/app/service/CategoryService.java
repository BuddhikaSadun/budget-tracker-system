package com.budget.app.service;

import com.budget.app.entity.Category;
import com.budget.app.respository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    // Create
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    // Get all
    public List<Category> getAllCategories(){
        return (List<Category>) categoryRepository.findAll();
    }

    // Get by id
    public Optional<Category> getCategoryById(Long id){
        return categoryRepository.findById(id);
    }

    // Update
    public Category updateCategory(Long id, Category updatedCategory){
        return categoryRepository.findById(id).map(category -> {
            category.setName(updatedCategory.getName());
            category.setUser(updatedCategory.getUser());
            return categoryRepository.save(category);
        }).orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }

    // Delete
    public void deleteCategory(Long id){
        if(!categoryRepository.existsById(id)){
            throw new RuntimeException("Category not found with id " + id);
        }
        categoryRepository.deleteById(id);
    }
}

