package com.wizer.booklibrary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wizer.booklibrary.dto.BookDTO;
import com.wizer.booklibrary.exception.ApiRequestException;
import com.wizer.booklibrary.exception.NotFoundException;
import com.wizer.booklibrary.model.Book;
import com.wizer.booklibrary.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public ResponseEntity<Book> addNewBook(Book book) {
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (book.getTitle() == null) {
            throw new ApiRequestException("Book title is required in the payload");
        } else if (book.getAuthor() == null) {
            throw new ApiRequestException("Book author is required in the payload");
        } else if (book.getYearReleased() == null) {
            throw new ApiRequestException("Book year released is required in the payload");
        } else if (book.getNoOfCopies() < 1) {
            throw new ApiRequestException("No of copies produced is required in the payload");
        }

        bookRepository.save(book);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public Book updateBook(Long id, BookDTO bookDTO) {
        if (bookDTO == null) {
            throw new ApiRequestException("Bad Request");
        }
        Optional<Book> findBook = bookRepository.findById(id);
        if (findBook.isPresent()) {
            Book book = findBook.get();
            book.setIsbn(bookDTO.getIsbn());
            book.setYearReleased(bookDTO.getYearReleased());
            book.setNoOfCopies(bookDTO.getNoOfCopies());
            book.setAvailable(bookDTO.isAvailable());
            book.setCategories(bookDTO.getCategories());

            bookRepository.save(book);
            return findBook.get();
        }

        throw new ApiRequestException("Book not found");
    }

    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }

    public ResponseEntity<Book> findBookById(long id) {
        Optional<Book> bookToFind = bookRepository.findById(id);
        if (!bookToFind.isPresent()) {
            throw new NotFoundException("Book with id " + id + " not found.");
        }
        return new ResponseEntity<>(bookToFind.get(), HttpStatus.OK);
    }

    public ResponseEntity<List<Book>> findBookByTitle(String title) {
        List<Book> bookTitle = bookRepository.findByTitleContaining(title);
        if (bookTitle.size() == 0) {
            throw new NotFoundException("Book with title " + title + " not found.");
        }
        return new ResponseEntity<>(bookTitle, HttpStatus.OK);
    }

    public ResponseEntity<Book> deleteBook(long id) {
        bookRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
