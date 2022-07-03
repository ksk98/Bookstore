package com.bookstore.Bookstore.controller.backend;

import com.bookstore.Bookstore.model.LoginUserRequest;
import com.bookstore.Bookstore.model.User;
import com.bookstore.Bookstore.repositories.UserRepository;
import com.bookstore.Bookstore.security.JwtUserDetailsService;
import com.bookstore.Bookstore.security.TokenManager;
import com.bookstore.Bookstore.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bookstore.Bookstore.api.UsersApi;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/")
public class UserController extends BaseBackendController implements UsersApi {
    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService userDetailsService;
    private final TokenManager tokenManager;

    public UserController(UserService userService, UserRepository userRepository, AuthenticationManager authenticationManager,
                          JwtUserDetailsService userDetailsService, TokenManager tokenManager) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenManager = tokenManager;
    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Void> deleteUserById(Integer userId) {
        verifyUserForAdminOrOwnerOrThrowUnauthorized(userId, userRepository);

        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<User> editUserById(Integer userId, User user) {
        verifyUserForAdminOrOwnerOrThrowUnauthorized(userId, userRepository);

        return new ResponseEntity<>(userService.updateUser(userId, user), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<User> getUserById(Integer userId) {
        verifyUserForAdminOrOwnerOrThrowUnauthorized(userId, userRepository);

        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
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
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }
}
