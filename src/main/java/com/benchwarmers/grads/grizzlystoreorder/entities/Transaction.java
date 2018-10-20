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
    @Column(name = "idTransaction", nullable = false)
    private Integer idTransaction;

    @Column(name = "idAccountForeign", nullable = false)
    private UUID idAccountForeign;

    @Column(name = "order_Total", nullable = false)
    private double order_Total;

    @CreationTimestamp
    @Column(name = "last_modified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_modified;

    @OneToMany(mappedBy="transaction")
    private List<TransactionItem> items = new ArrayList<>();

    public Integer getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Integer idTransaction) {
        this.idTransaction = idTransaction;
    }

    public UUID getIdAccountForeign() {
        return idAccountForeign;
    }

    public void setIdAccountForeign(UUID idAccountForeign) {
        this.idAccountForeign = idAccountForeign;
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
