package com.benchwarmers.grads.grizzlystoreorder.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

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
    @Column(name = "idCart", nullable = false)
    private Integer idCart;

    @Column(name = "idAccountForeign", nullable = false)
    @Type(type="uuid-char")
    private UUID idAccountForeign;

    @CreationTimestamp
    @Column(name = "last_modified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_modified;

    @OneToMany(mappedBy="cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @Column(name = "total", columnDefinition = "Decimal(10,2) default '0.00'")
    private double total;


    public Integer getIdCart() {
        return idCart;
    }

    public void setIdCart(Integer idCart) {
        this.idCart = idCart;
    }

    public UUID getIdAccountForeign() {
        return idAccountForeign;
    }

    public void setIdAccountForeign(UUID idAccountForeign) {
        this.idAccountForeign = idAccountForeign;
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
        items.forEach(cartItem -> cartItem.setCart(this));
        this.items = items;
    }

    public void addItem(CartItem item)
    {
        item.setCart(this);
        items.add(item);
    }

    public void removeItem(CartItem item)
    {
        items.remove(item);
    }


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
