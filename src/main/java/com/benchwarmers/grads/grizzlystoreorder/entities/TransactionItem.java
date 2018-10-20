package com.benchwarmers.grads.grizzlystoreorder.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction_item")
public class TransactionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idTransactionItem", nullable = false)
    private Integer idTransactionItem;

    @Column(name = "idItem", nullable = false)
    private Integer idItem;

    @Column(name = "itemQuantity", nullable = false)
    private Integer itemQuantity;

    @Column(name = "total", columnDefinition = "Decimal(10,2) default '0.00'")
    private double total;

    @Column( name = "itemPrice", nullable = false)
    private double itemPrice;

    @CreationTimestamp
    @Column(name = "last_modified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_modified;

    @ManyToOne
    @JoinColumn(name="id_Transaction_foreign", nullable=false)
    @JsonIgnore
    private Transaction transaction;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdTransactionItem() {
        return idTransactionItem;
    }

    public void setIdTransactionItem(Integer idTransactionItem) {
        this.idTransactionItem = idTransactionItem;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
