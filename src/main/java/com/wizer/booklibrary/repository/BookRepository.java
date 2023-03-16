package com.wizer.booklibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wizer.booklibrary.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
