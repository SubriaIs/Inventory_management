package com.subria.fi.services;

import com.subria.fi.model.Category;
import com.subria.fi.repositories.CategoryRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class CategoryService {
    private CategoryRepositoryImpl categoryRepository;

    public CategoryService(CategoryRepositoryImpl categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    public Category UpdateCategory(Category category) {
        return categoryRepository.update(category);
    }

    public void removeCategory(Long id) {
        categoryRepository.delete(id);
    }
}
