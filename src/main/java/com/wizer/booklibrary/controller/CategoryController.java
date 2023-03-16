package com.wizer.booklibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wizer.booklibrary.model.Category;
import com.wizer.booklibrary.service.CategoryService;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add-category")
    public ResponseEntity<Category> addBook(@RequestBody Category category) {
        return categoryService.addNewCategory(category);
    }

}
