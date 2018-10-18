package com.benchwarmers.grads.grizzlystoreorder.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction_item")
public class TransactionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_TransactionItem", nullable = false)
    private Integer id_TransactionItem;

    @Column(name = "id_Item", nullable = false)
    private Integer id_Item;

    @Column(name = "item_Quantity", nullable = false)
    private Integer item_Quantity;

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

    public Integer getItem_Quantity() {
        return item_Quantity;
    }

    public void setItem_Quantity(Integer item_Quantity) {
        this.item_Quantity = item_Quantity;
    }

    public Integer getId_Item() {
        return id_Item;
    }

    public void setId_Item(Integer id_Item) {
        this.id_Item = id_Item;
    }

    public Integer getId_TransactionItem() {
        return id_TransactionItem;
    }

    public void setId_TransactionItem(Integer id_TransactionItem) {
        this.id_TransactionItem = id_TransactionItem;
    }
}
