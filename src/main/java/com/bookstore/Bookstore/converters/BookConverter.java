package com.bookstore.Bookstore.converters;

import com.bookstore.Bookstore.model.Book;
import com.bookstore.Bookstore.models.BookEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class BookConverter {
    public Book toDTO(BookEntity entity) {
        Book out = new Book();

        out.setId(entity.getId());
        out.setTitle(entity.getTitle());
        out.setAuthor(entity.getAuthor());
        out.setPublisher(entity.getPublisher());

        return out;
    }

    public BookEntity toEntity(Book dto) {
        BookEntity out = new BookEntity();

        out.setTitle(dto.getTitle());
        out.setAuthor(dto.getAuthor());
        out.setPublisher(dto.getPublisher());

        return out;
    }
}
