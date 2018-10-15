package com.benchwarmers.grads.grizzlystoreorder.repositories;

import com.benchwarmers.grads.grizzlystoreorder.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItem_Repository extends JpaRepository<CartItem, Integer>
{
    void removeCartItemByIdItem(int id);
    CartItem findByIdItem(int id);
}
