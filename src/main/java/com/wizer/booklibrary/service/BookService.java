package com.wizer.booklibrary.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wizer.booklibrary.dto.BookDTO;
import com.wizer.booklibrary.exception.ApiRequestException;
import com.wizer.booklibrary.exception.NotFoundException;
import com.wizer.booklibrary.model.Book;
import com.wizer.booklibrary.model.Category;
import com.wizer.booklibrary.repository.BookRepository;
import com.wizer.booklibrary.repository.CategoryRepository;

@Service
public class BookService {

    /*
     * @Autowired
     * private BookRepository bookRepository;
     */

    private BookRepository bookRepository;
    private CategoryRepository categoryRepository;

    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    public Book addNewBook(BookDTO bookDTO) {
        if (bookDTO == null) {
            throw new ApiRequestException("Bad Request");
        } else if (bookDTO.getTitle() == null) {
            throw new ApiRequestException("Book title is required in the payload");
        } else if (bookDTO.getAuthor() == null) {
            throw new ApiRequestException("Book author is required in the payload");
        } else if (bookDTO.getYearReleased() == null) {
            throw new ApiRequestException("Book year released is required in the payload");
        } else if (bookDTO.getNoOfCopies() < 1) {
            throw new ApiRequestException("No of copies produced is required in the payload");
        } else if (bookDTO.getCategoryId() == null) {
            throw new ApiRequestException("Category id is required in the payload");
        }

        Optional<Category> findCategory = categoryRepository.findById(bookDTO.getCategoryId());

        if (findCategory.isPresent()) {
            Category bookCategory = findCategory.get();
            Book newBook = new Book();
            newBook.setTitle(bookDTO.getTitle());
            newBook.setAuthor(bookDTO.getAuthor());
            newBook.setPublisher(bookDTO.getPublisher());
            newBook.setIsbn(bookDTO.getIsbn());
            newBook.setYearReleased(bookDTO.getYearReleased());
            newBook.setNoOfCopies(bookDTO.getNoOfCopies());
            newBook.setCategory(bookCategory);
            newBook.setCreated(ZonedDateTime.now(ZoneId.systemDefault()));
            newBook.setUpdated(ZonedDateTime.now(ZoneId.systemDefault()));

            return bookRepository.save(newBook);
        } else {
            throw new ApiRequestException("Category ID " + bookDTO.getCategoryId() + " not found");
        }

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
            book.setPublisher(bookDTO.getPublisher());
            // book.setCategory(bookDTO.getCategory());

            Optional<Category> findCategory = categoryRepository.findById(bookDTO.getCategoryId());
            if (findCategory.isPresent()) {
                Category categoryToUpdate = findCategory.get();
                book.setCategory(categoryToUpdate);
                book.setUpdated(ZonedDateTime.now(ZoneId.systemDefault()));
                return bookRepository.save(book);
            } else {
                throw new ApiRequestException("Category ID " + bookDTO.getCategoryId() + " not found");
            }
        }

        throw new ApiRequestException("Book not found");
    }

    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }

    public Book findBookById(long id) {
        Optional<Book> bookToFind = bookRepository.findById(id);
        if (!bookToFind.isPresent()) {
            throw new NotFoundException("Book with id " + id + " not found.");
        }
        return bookToFind.get();
    }

    public List<Book> findBookByTitle(String title) {
        List<Book> bookTitle = bookRepository.findByTitleContaining(title);
        if (bookTitle.size() == 0) {
            throw new NotFoundException("Book with title " + title + " not found.");
        }
        return bookTitle;
    }

    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}
