package com.wizer.booklibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wizer.booklibrary.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
