package com.bookstore.Bookstore.services;

import com.bookstore.Bookstore.converters.OrderConverter;
import com.bookstore.Bookstore.models.OrderEntity;
import com.bookstore.Bookstore.models.UserEntity;
import com.bookstore.Bookstore.repositories.OrderRepository;
import com.bookstore.Bookstore.repositories.UserRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.bookstore.Bookstore.model.Order;

@Service
@Scope("singleton")
public class OrderService extends BaseService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    public OrderService(UserRepository userRepository, OrderRepository orderRepository, OrderConverter orderConverter) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderConverter = orderConverter;
    }

    public Order createOrderFromCartById(Integer userId) {
        OrderEntity entity = new OrderEntity();
        UserEntity target = getUserEntityOrThrowNotFound(userId, userRepository);

        entity.setRecipient(target);
        entity.getContent().addAll(target.getCart());

        target.getCart().clear();
        userRepository.save(target);

        return orderConverter.toDTO(orderRepository.save(entity));
    }

    public Order setSent(Integer orderId, boolean status) {
        OrderEntity target = getOrderEntityOrThrowNotFound(orderId, orderRepository);

        target.setSent(status);

        return orderConverter.toDTO(orderRepository.save(target));
    }

    public Order setPaid(Integer orderId, boolean status) {
        OrderEntity target = getOrderEntityOrThrowNotFound(orderId, orderRepository);

        target.setPaid(status);

        return orderConverter.toDTO(orderRepository.save(target));
    }

    public Order setComplete(Integer orderId, boolean status) {
        OrderEntity target = getOrderEntityOrThrowNotFound(orderId, orderRepository);

        target.setPaid(status);

        return orderConverter.toDTO(orderRepository.save(target));
    }
}
