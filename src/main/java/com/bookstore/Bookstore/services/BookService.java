package com.bookstore.Bookstore.services;

import com.bookstore.Bookstore.converters.BookConverter;
import com.bookstore.Bookstore.models.BookEntity;
import com.bookstore.Bookstore.repositories.BookRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.bookstore.Bookstore.model.Book;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope("singleton")
public class BookService extends BaseService {
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
        BookEntity target = getBookEntityOrThrowNotFound(id, bookRepository);

        return bookConverter.toDTO(target);
    }

    public List<Book> getBooks() {
        return bookRepository
                .findAll()
                .stream()
                .map(bookConverter::toDTO)
                .collect(Collectors.toList());
    }

    public Book updateBook(Book book) {
        BookEntity entity = getBookEntityOrThrowNotFound(book.getId(), bookRepository);

        entity.setTitle(book.getTitle());
        entity.setAuthor(book.getAuthor());
        entity.setPublisher(book.getPublisher());

        return bookConverter.toDTO(bookRepository.save(entity));
    }

    public void deleteBook(Integer id) {
        getBookEntityOrThrowNotFound(id, bookRepository);

        bookRepository.deleteById(id);
    }
}
