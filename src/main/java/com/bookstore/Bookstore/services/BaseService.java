package com.bookstore.Bookstore.services;

import com.bookstore.Bookstore.exceptions.ConflictException;
import com.bookstore.Bookstore.exceptions.ResourceNotFoundException;
import com.bookstore.Bookstore.models.BookEntity;
import com.bookstore.Bookstore.models.OrderEntity;
import com.bookstore.Bookstore.models.UserEntity;
import com.bookstore.Bookstore.repositories.BookRepository;
import com.bookstore.Bookstore.repositories.OrderRepository;
import com.bookstore.Bookstore.repositories.UserRepository;

import java.util.Optional;

public abstract class BaseService {
    protected UserEntity getUserEntityOrThrowNotFound(Integer userId, UserRepository userRepository) {
        Optional<UserEntity> targetQuery = userRepository.findById(userId);
        if (targetQuery.isEmpty())
            throw new ResourceNotFoundException("No user of id " + userId + " exists.");

        return targetQuery.get();
    }

    protected BookEntity getBookEntityOrThrowNotFound(Integer bookId, BookRepository bookRepository) {
        Optional<BookEntity> targetQuery = bookRepository.findById(bookId);
        if (targetQuery.isEmpty())
            throw new ResourceNotFoundException("No book of id " + bookId + " exists.");

        return targetQuery.get();
    }

    protected OrderEntity getOrderEntityOrThrowNotFound(Integer orderId, OrderRepository orderRepository) {
        Optional<OrderEntity> targetQuery = orderRepository.findById(orderId);
        if (targetQuery.isEmpty())
            throw new ResourceNotFoundException("No order of id " + orderId + " exists.");

        return targetQuery.get();
    }

    protected void verifyUniqueFieldsForUser(UserEntity target, UserRepository userRepository) {
        UserEntity nameCheck = userRepository.findByUsername(target.getUsername());
        if (nameCheck != null && !nameCheck.getId().equals(target.getId()))
            throw new ConflictException("Username " + target.getUsername() + " already taken.");

        UserEntity emailCheck = userRepository.findByEmail(target.getEmail());
        if (emailCheck != null && !emailCheck.getId().equals(target.getId()))
            throw new ConflictException("Email " + target.getEmail() + " already taken.");
    }
}
