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

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity recipient;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Column(name = "is_sent")
    private Boolean isSent;

    @Column(name = "is_complete")
    private Boolean isComplete;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_relations",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<BookEntity> content;

    public OrderEntity() {
        content = new LinkedList<>();
        isPaid = false;
        isSent = false;
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

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Boolean getSent() {
        return isSent;
    }

    public void setSent(Boolean sent) {
        isSent = sent;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public List<BookEntity> getContent() {
        return content;
    }
}
