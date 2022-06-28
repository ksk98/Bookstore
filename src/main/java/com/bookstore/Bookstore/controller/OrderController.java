package com.bookstore.Bookstore.controller;

import com.bookstore.Bookstore.model.Order;
import com.bookstore.Bookstore.repositories.UserRepository;
import com.bookstore.Bookstore.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bookstore.Bookstore.api.OrdersApi;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/")
public class OrderController extends BaseController implements OrdersApi {
    private final OrderService orderService;
    private final UserRepository userRepository;

    public OrderController(OrderService orderService, UserRepository userRepository) {
        this.orderService = orderService;
        this.userRepository = userRepository;
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Order> createOrderFromCartById(Integer userId) {
        verifyUserForAdminOrOwnerOrThrowUnauthorized(userId, userRepository);

        return new ResponseEntity<>(orderService.createOrderFromCartById(userId), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> setCompletionStatusForOrderById(Integer orderId, Boolean body) {
        orderService.setComplete(orderId, body);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> setPaymentStatusForOrderById(Integer orderId, Boolean body) {
        orderService.setPaid(orderId, body);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> setSentStatusForOrderById(Integer orderId, Boolean body) {
        orderService.setSent(orderId, body);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
