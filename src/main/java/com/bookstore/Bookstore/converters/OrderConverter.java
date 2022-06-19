package com.bookstore.Bookstore.converters;

import com.bookstore.Bookstore.models.OrderEntity;
import com.bookstore.Bookstore.repositories.OrderRepository;
import com.bookstore.Bookstore.repositories.UserRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.bookstore.Bookstore.model.Order;
import com.bookstore.Bookstore.model.Book;

import java.util.LinkedList;
import java.util.List;

@Service
@Scope("singleton")
public class OrderConverter {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookConverter bookConverter;

    public OrderConverter(OrderRepository orderRepository, UserRepository userRepository,
                          BookConverter bookConverter) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.bookConverter = bookConverter;
    }

    public Order toDTO(OrderEntity entity) {
        Order out = new Order();

        out.setId(entity.getId());
        out.setUserId(entity.getRecipient().getId());
        out.setIsPaid(entity.getPaid());
        out.setIsSent(entity.getSent());

        List<Book> content = new LinkedList<>();
        entity.getContent().stream().iterator().forEachRemaining(book -> content.add(bookConverter.toDTO(book)));
        out.setItems(content);

        return out;
    }

    public OrderEntity toEntity(Order dto) {
        OrderEntity out = new OrderEntity();

        out.setRecipient(userRepository.findById(dto.getUserId()).orElse(null));
        out.setPaid(dto.getIsPaid());
        out.setSent(dto.getIsSent());

        return out;
    }
}
