package com.bookstore.Bookstore.services;

import com.bookstore.Bookstore.converters.UserConverter;
import com.bookstore.Bookstore.models.RoleEntity;
import com.bookstore.Bookstore.models.UserEntity;
import com.bookstore.Bookstore.repositories.RoleRepository;
import com.bookstore.Bookstore.repositories.UserRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bookstore.Bookstore.model.User;

@Service
@Scope("singleton")
public class UserService extends BaseService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserConverter userConverter, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        UserEntity entity = new UserEntity();

        entity.setUsername(user.getUsername());
        entity.setPassword(passwordEncoder.encode(user.getPassword()));
        entity.setEmail(user.getEmail());
        entity.getRoles().add(roleRepository.getRoleByName(RoleEntity.ROLE_USER));

        verifyUniqueFieldsForUser(entity, userRepository);

        return userConverter.toDTO(userRepository.save(entity));
    }

    public User getUser(Integer userId) {
        UserEntity target = getUserEntityOrThrowNotFound(userId, userRepository);

        return userConverter.toDTO(target);
    }

    public User updateUser(Integer userId, User user) {
        UserEntity target = getUserEntityOrThrowNotFound(userId, userRepository);

        target.setUsername(user.getUsername());
        target.setPassword(passwordEncoder.encode(user.getPassword()));
        target.setEmail(user.getEmail());

        verifyUniqueFieldsForUser(target, userRepository);

        return userConverter.toDTO(userRepository.save(target));
    }

    public void deleteUser(Integer id) {
        UserEntity target = getUserEntityOrThrowNotFound(id, userRepository);

        userRepository.delete(target);
    }
}
