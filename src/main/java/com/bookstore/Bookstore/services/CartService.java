package com.bookstore.Bookstore.services;

import com.bookstore.Bookstore.converters.BookConverter;
import com.bookstore.Bookstore.models.BookEntity;
import com.bookstore.Bookstore.models.UserEntity;
import com.bookstore.Bookstore.repositories.BookRepository;
import com.bookstore.Bookstore.repositories.UserRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.bookstore.Bookstore.model.Book;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope("singleton")
public class CartService extends BaseService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BookConverter bookConverter;

    public CartService(UserRepository userRepository, BookRepository bookRepository, BookConverter bookConverter) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
    }

    public List<Book> getCart(Integer userId) {
        UserEntity target = getUserEntityOrThrowNotFound(userId, userRepository);

        return target.getCart().stream().map(bookConverter::toDTO).collect(Collectors.toList());
    }

    public void clearCart(Integer userId) {
        UserEntity target = getUserEntityOrThrowNotFound(userId, userRepository);

        target.getCart().clear();
        userRepository.save(target);
    }

    public List<Book> addItemToCart(Integer bookId, Integer userId) {
        UserEntity target = getUserEntityOrThrowNotFound(userId, userRepository);
        BookEntity item = getBookEntityOrThrowNotFound(bookId, bookRepository);

        target.getCart().add(item);
        target = userRepository.save(target);

        return target.getCart().stream().map(bookConverter::toDTO).collect(Collectors.toList());
    }

    public List<Book> removeItemFromCart(Integer bookId, Integer userId) {
        UserEntity target = getUserEntityOrThrowNotFound(userId, userRepository);
        BookEntity item = getBookEntityOrThrowNotFound(bookId, bookRepository);

        if (!target.getCart().contains(item))
            throw new OpenApiResourceNotFoundException(
                    "Cart for user of id " + userId + " doesn't contain book of id " + bookId);

        target.getCart().remove(item);

        return userRepository.save(target)
                .getCart()
                .stream()
                .map(bookConverter::toDTO)
                .collect(Collectors.toList());
    }
}
