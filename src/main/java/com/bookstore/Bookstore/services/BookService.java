package com.bookstore.Bookstore.services;

import com.bookstore.Bookstore.converters.BookConverter;
import com.bookstore.Bookstore.models.BookEntity;
import com.bookstore.Bookstore.repositories.BookRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.bookstore.Bookstore.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Scope("singleton")
public class BookService {
    private final BookRepository bookRepository;
    private final BookConverter bookConverter;

    public BookService(BookRepository bookRepository, BookConverter bookConverter) {
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
    }

    public Book createBook(Book book) {
        BookEntity entity = bookConverter.toEntity(book);
        entity = bookRepository.save(entity);
        return bookConverter.toDTO(entity);
    }

    public Book getBook(Integer id) {
        Optional<BookEntity> entity = bookRepository.findById(id);
        if (entity.isEmpty())
            throw new OpenApiResourceNotFoundException("No book of id " + id + " exists.");

        return bookConverter.toDTO(entity.get());
    }

    public List<Book> getBooks() {
        return bookRepository
                .findAll()
                .stream()
                .map(bookConverter::toDTO)
                .collect(Collectors.toList());
    }

    public Book updateBook(Book book) {
        Optional<BookEntity> entityQuery = bookRepository.findById(book.getId());
        if (entityQuery.isEmpty())
            throw new OpenApiResourceNotFoundException("No book of id " + book.getId() + " exists.");

        BookEntity entity = entityQuery.get();
        entity.setTitle(book.getTitle());
        entity.setAuthor(book.getAuthor());
        entity.setPublisher(book.getPublisher());

        return bookConverter.toDTO(bookRepository.save(entity));
    }

    public void deleteBook(Integer id) {
        Optional<BookEntity> entityQuery = bookRepository.findById(id);
        if (entityQuery.isEmpty())
            throw new OpenApiResourceNotFoundException("No book of id " + id + " exists.");

        bookRepository.deleteById(id);
    }
}
