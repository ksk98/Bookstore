package com.bookstore.Bookstore.repositories;

import com.bookstore.Bookstore.models.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    @Query(value = "SELECT r FROM Roles r WHERE r.name = ?1")
    RoleEntity getRoleByName(String name);
}
