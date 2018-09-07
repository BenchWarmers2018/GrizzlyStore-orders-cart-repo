package com.benchwarmers.grads.grizzlystoreorder.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_transaction", nullable = false)
    private Integer id_transaction;

    @Column(name = "id_Account_Foreign", nullable = false)
    private UUID id_Account_Foreign;

    @Column(name = "order_Total", nullable = false)
    private double order_Total;

    @CreationTimestamp
    @Column(name = "last_modified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_modified;

    @OneToMany(mappedBy="transaction")
    private List<TransactionItem> items = new ArrayList<>();

}
