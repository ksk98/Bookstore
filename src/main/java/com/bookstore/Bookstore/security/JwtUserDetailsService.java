package com.bookstore.Bookstore.security;

import com.bookstore.Bookstore.converters.UserConverter;
import com.bookstore.Bookstore.models.UserEntity;
import com.bookstore.Bookstore.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public JwtUserDetailsService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity target = userRepository.findByUsername(username);
        if (target == null)
            throw new UsernameNotFoundException("No user of name " + username + " exists.");

        return new User(target.getUsername(), target.getPassword(), target.getAuthorities());
    }
}
