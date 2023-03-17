package com.wizer.booklibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/v1")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return bookService.addNewBook(book);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBok(@PathVariable("id") Long id, @RequestBody BookDTO bookDTO) {
        return bookService.updateBook(id, bookDTO);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> findAllBook() {
        return new ResponseEntity<>(bookService.findAllBook(), HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> findById(@PathVariable("id") long id) {
        return bookService.findBookById(id);
    }

    @GetMapping("/books/{title}")
    public ResponseEntity<List<Book>> findByTitle(@PathVariable("title") String title) {
        return bookService.findBookByTitle(title);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") Long id) {
        return bookService.deleteBook(id);
    }

}
