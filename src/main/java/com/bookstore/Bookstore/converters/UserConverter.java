package com.bookstore.Bookstore.converters;

import com.bookstore.Bookstore.models.UserEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.bookstore.Bookstore.model.User;
import com.bookstore.Bookstore.model.Book;

import java.util.LinkedList;
import java.util.List;

@Service
@Scope("singleton")
public class UserConverter {
    private final BookConverter bookConverter;

    public UserConverter(BookConverter bookConverter) {
        this.bookConverter = bookConverter;
    }

    public User toDTO(UserEntity entity) {
        User out = new User();

        out.setId(entity.getId());
        out.setUsername(entity.getUsername());
        out.setPassword(entity.getPassword());
        out.setEmail(entity.getEmail());

        List<Book> cart = new LinkedList<>();
        entity.getCart().iterator().forEachRemaining(book -> cart.add(bookConverter.toDTO(book)));
        out.setCart(cart);

        return out;
    }

    public UserEntity toEntity(User dto) {
        UserEntity out = new UserEntity();

        out.setUsername(dto.getUsername());
        out.setPassword(dto.getPassword());
        out.setEmail(dto.getEmail());

        return out;
    }
}
