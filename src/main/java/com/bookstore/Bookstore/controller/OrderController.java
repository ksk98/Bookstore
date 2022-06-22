package com.bookstore.Bookstore.controller;

import com.bookstore.Bookstore.model.Order;
import com.bookstore.Bookstore.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bookstore.Bookstore.api.OrdersApi;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/")
public class OrderController implements OrdersApi {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ResponseEntity<Order> createOrderFromCartById(Integer userId) {
        return new ResponseEntity<>(orderService.createOrderFromCartById(userId), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> setCompletionStatusForOrderById(Integer orderId, Boolean body) {
        orderService.setComplete(orderId, body);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> setPaymentStatusForOrderById(Integer orderId, Boolean body) {
        orderService.setPaid(orderId, body);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> setSentStatusForOrderById(Integer orderId, Boolean body) {
        orderService.setSent(orderId, body);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
