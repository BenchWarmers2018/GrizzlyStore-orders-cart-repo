package com.benchwarmers.grads.grizzlystoreorder.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Cart", nullable = false)
    private Integer id_Cart;

    @Column(name = "id_Account_Foreign", nullable = false)
    private UUID id_Account_Foreign;

    @CreationTimestamp
    @Column(name = "last_modified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_modified;

    @OneToMany(mappedBy="cart", cascade = CascadeType.ALL)
    private List<CartItem> items = new ArrayList<>();

    public Integer getId_Cart() {
        return id_Cart;
    }

    public void setId_Cart(Integer id_Cart) {
        this.id_Cart = id_Cart;
    }

    public UUID getId_Account_Foreign() {
        return id_Account_Foreign;
    }

    public void setId_Account_Foreign(UUID id_Account_Foreign) {
        this.id_Account_Foreign = id_Account_Foreign;
    }

    public Date getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(Date last_modified) {
        this.last_modified = last_modified;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }


}
