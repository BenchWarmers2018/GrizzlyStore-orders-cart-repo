package com.benchwarmers.grads.grizzlystoreorder.repositories;

import com.benchwarmers.grads.grizzlystoreorder.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface Cart_Repository extends JpaRepository<Cart, Integer> {
    Cart findCartByIdAccountForeign(UUID id);
    Boolean existsByIdAccountForeign(UUID id);
}
