package com.benchwarmers.grads.grizzlystoreorder.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="cart_list")
public class CartList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_CartList", nullable = false)
    private Integer id_CartList;


    @Column(name = "id_Item", nullable = false)
    private Integer id_Item;

    @Column(name = "item_Quantity", nullable = false)
    private Integer item_Quantity;

    @CreationTimestamp
    @Column(name = "last_modified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_modified;

    @ManyToOne
    @JoinColumn(name = "id_Cart_foreign")
    private Cart cart;
}
