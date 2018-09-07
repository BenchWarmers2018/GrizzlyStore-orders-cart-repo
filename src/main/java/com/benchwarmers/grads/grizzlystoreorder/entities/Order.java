package com.benchwarmers.grads.grizzlystoreorder.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Order", nullable = false)
    private Integer id_Order;

    @Column(name = "id_Account_Foreign", nullable = false)
    private UUID id_Account_Foreign;

    @Column(name = "order_Total", nullable = false)
    private double order_Total;

    @CreationTimestamp
    @Column(name = "last_modified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_modified;
}
