package com.wizer.booklibrary.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wizer.booklibrary.exception.ApiRequestException;
import com.wizer.booklibrary.exception.NotFoundException;
import com.wizer.booklibrary.model.Category;
import com.wizer.booklibrary.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category addNewCategory(Category category) {

        if (category == null) {
            throw new ApiRequestException("Bad Request");
        } else if (category.getName() == null) {
            throw new ApiRequestException("Category name is required in the payload");
        } else if (category.getDescription() == null) {
            throw new ApiRequestException("Category description is required in the payload");
        }

        category.setCreated(ZonedDateTime.now(ZoneId.systemDefault()));
        category.setUpdated(ZonedDateTime.now(ZoneId.systemDefault()));

        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category categoryDTO) {
        if (categoryDTO == null) {
            throw new ApiRequestException("Bad Request");
        }
        Optional<Category> findCategory = categoryRepository.findById(id);
        if (findCategory.isPresent()) {
            Category category = findCategory.get();
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());

            categoryRepository.save(category);
            return findCategory.get();
        } else {
            throw new NotFoundException("Category not found.");
        }
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(long id) {
        Optional<Category> categoryToFind = categoryRepository.findById(id);
        if (!categoryToFind.isPresent()) {
            throw new NotFoundException("Category with id " + id + " not found.");
        }
        return categoryToFind.get();
    }

    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }
}
