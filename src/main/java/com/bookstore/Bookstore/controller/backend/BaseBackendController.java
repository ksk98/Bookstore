package com.bookstore.Bookstore.controller.backend;

import com.bookstore.Bookstore.models.RoleEntity;
import com.bookstore.Bookstore.models.UserEntity;
import com.bookstore.Bookstore.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;

public abstract class BaseBackendController {
    protected void verifyUserForAdminOrOwnerOrThrowUnauthorized(Integer targetUserId, UserRepository userRepository) {
        UserEntity caller = userRepository.findByUsername(
                ((org.springframework.security.core.userdetails.User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal())
                        .getUsername());

        boolean clearToEdit = false;
        for (RoleEntity role: caller.getRoles()) {
            if (role.getName().equals(RoleEntity.ROLE_ADMIN)) {
                clearToEdit = true;
                break;
            } else if (role.getName().equals(RoleEntity.ROLE_USER)) {
                if (caller.getId().equals(targetUserId))
                    clearToEdit = true;
            }
        }

        if (!clearToEdit)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to edit this user.");
    }
}
