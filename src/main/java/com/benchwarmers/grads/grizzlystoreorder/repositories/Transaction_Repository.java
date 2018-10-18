package com.benchwarmers.grads.grizzlystoreorder.repositories;


import com.benchwarmers.grads.grizzlystoreorder.entities.Cart;
import com.benchwarmers.grads.grizzlystoreorder.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Transaction_Repository extends JpaRepository<Transaction, Integer> {
}
