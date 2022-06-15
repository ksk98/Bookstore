package com.bookstore.Bookstore.models;

import javax.persistence.*;

@Entity(name = "Roles")
public class RoleEntity {
    private static final String
            ROLE_USER = "ROLE_USER",
            ROLE_ADMIN = "ROLE_ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    public RoleEntity() {

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
