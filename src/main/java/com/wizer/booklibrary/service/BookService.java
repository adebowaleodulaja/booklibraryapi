package com.wizer.booklibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wizer.booklibrary.model.Book;
import com.wizer.booklibrary.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public ResponseEntity<Book> addNewBook(Book book) {
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        bookRepository.save(book);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
