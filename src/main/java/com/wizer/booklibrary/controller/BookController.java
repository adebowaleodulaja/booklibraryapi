package com.wizer.booklibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wizer.booklibrary.dto.BookDTO;
import com.wizer.booklibrary.model.Book;
import com.wizer.booklibrary.service.BookService;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add-book")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return bookService.addNewBook(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBok(@PathVariable("id") Long id, @RequestBody BookDTO bookDTO) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") Long id) {
        return null;
    }

}
