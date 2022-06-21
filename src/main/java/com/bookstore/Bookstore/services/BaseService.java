package com.bookstore.Bookstore.services;

import com.bookstore.Bookstore.models.BookEntity;
import com.bookstore.Bookstore.models.OrderEntity;
import com.bookstore.Bookstore.models.UserEntity;
import com.bookstore.Bookstore.repositories.BookRepository;
import com.bookstore.Bookstore.repositories.OrderRepository;
import com.bookstore.Bookstore.repositories.UserRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;

import java.util.Optional;

public abstract class BaseService {
    protected UserEntity getUserEntityOrThrowNotFound(Integer userId, UserRepository userRepository) {
        Optional<UserEntity> targetQuery = userRepository.findById(userId);
        if (targetQuery.isEmpty())
            throw new OpenApiResourceNotFoundException("No user of id " + userId + " exists.");

        return targetQuery.get();
    }

    protected BookEntity getBookEntityOrThrowNotFound(Integer bookId, BookRepository bookRepository) {
        Optional<BookEntity> targetQuery = bookRepository.findById(bookId);
        if (targetQuery.isEmpty())
            throw new OpenApiResourceNotFoundException("No book of id " + bookId + " exists.");

        return targetQuery.get();
    }

    protected OrderEntity getOrderEntityOrThrowNotFound(Integer orderId, OrderRepository orderRepository) {
        Optional<OrderEntity> targetQuery = orderRepository.findById(orderId);
        if (targetQuery.isEmpty())
            throw new OpenApiResourceNotFoundException("No order of id " + orderId + " exists.");

        return targetQuery.get();
    }
}
