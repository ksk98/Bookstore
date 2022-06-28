package com.bookstore.Bookstore.controller;

import com.bookstore.Bookstore.model.Book;
import com.bookstore.Bookstore.repositories.UserRepository;
import com.bookstore.Bookstore.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bookstore.Bookstore.api.CartsApi;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/")
public class CartController extends BaseController implements CartsApi {
    private final CartService cartService;
    private final UserRepository userRepository;

    public CartController(CartService cartService, UserRepository userRepository) {
        this.cartService = cartService;
        this.userRepository = userRepository;
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Void> addItemToCartById(Integer userId, Integer itemId) {
        verifyUserForAdminOrOwnerOrThrowUnauthorized(userId, userRepository);

        cartService.addItemToCart(userId, itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Void> clearCartContentById(Integer userId) {
        verifyUserForAdminOrOwnerOrThrowUnauthorized(userId, userRepository);

        cartService.clearCart(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<Book>> getCartContentById(Integer userId) {
        verifyUserForAdminOrOwnerOrThrowUnauthorized(userId, userRepository);

        return new ResponseEntity<>(cartService.getCart(userId), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Void> removeItemFromCartById(Integer userId, Integer itemId) {
        verifyUserForAdminOrOwnerOrThrowUnauthorized(userId, userRepository);

        cartService.removeItemFromCart(userId, itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
