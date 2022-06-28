package com.bookstore.Bookstore.controller;

import com.bookstore.Bookstore.model.LoginUserRequest;
import com.bookstore.Bookstore.model.User;
import com.bookstore.Bookstore.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bookstore.Bookstore.api.UsersApi;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/")
public class UserController implements UsersApi {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteUserById(Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<User> editUserById(Integer userId, User user) {
        return new ResponseEntity<>(userService.updateUser(userId, user), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<User> getUserById(Integer userId) {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<String> loginUser(LoginUserRequest loginUserRequest) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUserRequest.getUsername(),
                            loginUserRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginUserRequest.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return new ResponseEntity<>(jwtToken, HttpStatus.ACCEPTED);
    }
}
