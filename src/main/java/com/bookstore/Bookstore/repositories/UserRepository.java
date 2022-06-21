package com.bookstore.Bookstore.repositories;

import com.bookstore.Bookstore.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query(value = "SELECT u FROM Users u WHERE u.username = ?1")
    UserEntity findByUsername(String username);

    @Query(value = "SELECT u FROM Users u WHERE u.email = ?1")
    UserEntity findByEmail(String email);
}
