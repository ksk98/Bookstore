package com.bookstore.Bookstore.repositories;

import com.bookstore.Bookstore.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
