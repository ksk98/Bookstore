package com.bookstore.Bookstore.controller;

import com.bookstore.Bookstore.model.Book;
import com.bookstore.Bookstore.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bookstore.Bookstore.api.CartsApi;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/")
public class CartController implements CartsApi {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public ResponseEntity<Void> addItemToCartById(Integer userId, Integer itemId) {
        cartService.addItemToCart(userId, itemId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> clearCartContentById(Integer userId) {
        cartService.clearCart(userId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<List<Book>> getCartContentById(Integer userId) {
        return new ResponseEntity<>(cartService.getCart(userId), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> removeItemFromCartById(Integer userId, Integer itemId) {
        cartService.removeItemFromCart(userId, itemId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
