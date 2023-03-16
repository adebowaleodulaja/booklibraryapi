package com.wizer.booklibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wizer.booklibrary.model.Category;
import com.wizer.booklibrary.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<Category> addNewCategory(Category category) {

        if (category == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        categoryRepository.save(category);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
