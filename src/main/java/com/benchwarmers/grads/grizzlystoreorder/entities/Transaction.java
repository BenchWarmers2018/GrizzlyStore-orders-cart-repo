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

    public Integer getId_transaction() {
        return id_transaction;
    }

    public void setId_transaction(Integer id_transaction) {
        this.id_transaction = id_transaction;
    }

    public UUID getId_Account_Foreign() {
        return id_Account_Foreign;
    }

    public void setId_Account_Foreign(UUID id_Account_Foreign) {
        this.id_Account_Foreign = id_Account_Foreign;
    }

    public double getOrder_Total() {
        return order_Total;
    }

    public void setOrder_Total(double order_Total) {
        this.order_Total = order_Total;
    }

    public List<TransactionItem> getItems() {
        return items;
    }

    public void addItems(TransactionItem item) {
        item.setTransaction(this);
        items.add(item);
    }

    public void setItems(List<TransactionItem> items) {
        items.forEach(transactionItem -> transactionItem.setTransaction(this));
        this.items = items;
    }
}
