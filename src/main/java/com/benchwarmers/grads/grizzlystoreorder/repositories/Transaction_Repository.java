package com.benchwarmers.grads.grizzlystoreorder.repositories;


import com.benchwarmers.grads.grizzlystoreorder.entities.Cart;
import com.benchwarmers.grads.grizzlystoreorder.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface Transaction_Repository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByIdAccountForeign(UUID id);
    Boolean existsByIdAccountForeign(UUID id);
}
