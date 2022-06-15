package com.bookstore.Bookstore.models;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity(name = "Orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @OneToOne(mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private UserEntity recipient;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<BookEntity> content;

    public OrderEntity() {
        content = new LinkedList<>();
    }

    public Integer getId() {
        return id;
    }

    public UserEntity getRecipient() {
        return recipient;
    }

    public void setRecipient(UserEntity recipient) {
        this.recipient = recipient;
    }

    public List<BookEntity> getContent() {
        return content;
    }
}
