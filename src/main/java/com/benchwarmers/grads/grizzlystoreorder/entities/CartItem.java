package com.benchwarmers.grads.grizzlystoreorder.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCartItem", nullable = false)
    private Integer idCartItem;


    @Column(name = "idItem", nullable = false)
    private Integer idItem;

    @Column(name = "itemQuantity", nullable = false)
    private Integer itemQuantity;

    @CreationTimestamp
    @Column(name = "last_modified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_modified;

    @Column( name = "itemPrice", nullable = false)
    private double itemPrice;

    @Column(name = "total", columnDefinition = "Decimal(10,2) default '0.00'")
    private double total;

    @ManyToOne
    @JoinColumn(name="id_Cart_foreign", nullable=false)
    @JsonIgnore
    private Cart cart;

    //Getters and setters


    public Integer getIdCartItem() {
        return idCartItem;
    }

    public void setIdCartItem(Integer idCartItem) {
        this.idCartItem = idCartItem;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Date getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(Date last_modified) {
        this.last_modified = last_modified;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
