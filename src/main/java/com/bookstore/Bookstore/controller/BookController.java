package com.bookstore.Bookstore.controller;

import com.bookstore.Bookstore.api.BooksApi;
import com.bookstore.Bookstore.model.Book;
import com.bookstore.Bookstore.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/")
public class BookController implements BooksApi {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public ResponseEntity<Book> createBook(Book book) {
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteBookById(Integer bookId) {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Book> editBookById(Integer bookId, Book book) {
        return new ResponseEntity<>(bookService.updateBook(bookId, book), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Book> getBookById(Integer bookId) {
        return new ResponseEntity<>(bookService.getBook(bookId), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<>(bookService.getBooks(), HttpStatus.ACCEPTED);
    }
}
