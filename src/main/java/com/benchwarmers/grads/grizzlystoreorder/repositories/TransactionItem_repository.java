package com.benchwarmers.grads.grizzlystoreorder.repositories;


import com.benchwarmers.grads.grizzlystoreorder.entities.TransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionItem_repository extends JpaRepository<TransactionItem, Integer> {

}


