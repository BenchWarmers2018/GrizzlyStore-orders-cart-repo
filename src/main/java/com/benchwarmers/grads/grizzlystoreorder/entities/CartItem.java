package com.benchwarmers.grads.grizzlystoreorder.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_CartItem", nullable = false)
    private Integer id_CartItem;


    @Column(name = "id_Item", nullable = false)
    private Integer id_Item;

    @Column(name = "item_Quantity", nullable = false)
    private Integer item_Quantity;

    @CreationTimestamp
    @Column(name = "last_modified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_modified;


    @ManyToOne
    @JoinColumn(name="id_Cart_foreign", nullable=false)
    private Cart cart;

    //Getters and setters


    public Integer getId_CartItem() {
        return id_CartItem;
    }

    public void setId_CartItem(Integer id_CartItem) {
        this.id_CartItem = id_CartItem;
    }

    public Integer getId_Item() {
        return id_Item;
    }

    public void setId_Item(Integer id_Item) {
        this.id_Item = id_Item;
    }

    public Integer getItem_Quantity() {
        return item_Quantity;
    }

    public void setItem_Quantity(Integer item_Quantity) {
        this.item_Quantity = item_Quantity;
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
}
