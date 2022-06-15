package com.bookstore.Bookstore.repositories;

import com.bookstore.Bookstore.models.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
}
