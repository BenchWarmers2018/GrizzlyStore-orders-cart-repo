package com.benchwarmers.grads.grizzlystoreorder.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
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

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<CartList> items;


}
