package com.bookstore.Bookstore.services;

import com.bookstore.Bookstore.converters.UserConverter;
import com.bookstore.Bookstore.models.UserEntity;
import com.bookstore.Bookstore.repositories.UserRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.bookstore.Bookstore.model.User;

@Service
@Scope("singleton")
public class UserService extends BaseService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public User createUser(User user) {
        UserEntity entity = new UserEntity();

        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setEmail(user.getEmail());

        verifyUniqueFieldsForUser(entity, userRepository);

        return userConverter.toDTO(userRepository.save(entity));
    }

    public User getUser(Integer userId) {
        UserEntity target = getUserEntityOrThrowNotFound(userId, userRepository);

        return userConverter.toDTO(target);
    }

    public User updateUser(User user) {
        UserEntity target = getUserEntityOrThrowNotFound(user.getId(), userRepository);

        target.setUsername(user.getUsername());
        target.setPassword(user.getPassword());
        target.setEmail(user.getEmail());

        verifyUniqueFieldsForUser(target, userRepository);

        return userConverter.toDTO(userRepository.save(target));
    }

    public void deleteUser(Integer id) {
        UserEntity target = getUserEntityOrThrowNotFound(id, userRepository);

        userRepository.delete(target);
    }
}
