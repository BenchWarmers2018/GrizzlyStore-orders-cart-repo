package com.benchwarmers.grads.grizzlystoreorder.controllers;


import com.benchwarmers.grads.grizzlystoreorder.entities.Cart;
import com.benchwarmers.grads.grizzlystoreorder.entities.Transaction;
import com.benchwarmers.grads.grizzlystoreorder.entities.TransactionItem;
import com.benchwarmers.grads.grizzlystoreorder.repositories.CartItem_Repository;
import com.benchwarmers.grads.grizzlystoreorder.repositories.Cart_Repository;
import com.benchwarmers.grads.grizzlystoreorder.repositories.Transaction_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(path = "/process")
public class OrderController
{
    @Autowired
    Cart_Repository cart_repository;

    @Autowired
    CartItem_Repository cartItem_repository;

    @Autowired
    Transaction_Repository transaction_repository;

    @RequestMapping(path = "/order", method = RequestMethod.POST)
    public Transaction processOrder(@RequestBody Cart cart)
    {
        //Grabs the UUID from the cart given
        UUID accountUUID = cart.getIdAccountForeign();
        //Looks for the cart by the account UUID
        Cart confirmedOrder = cart_repository.findCartByIdAccountForeign(accountUUID);
        //Where the new transaction will be added to.
        Transaction transaction = new Transaction();

        //Check if this statement works.
        if(confirmedOrder.getIdCart().equals(cart.getIdCart()))
        {
            //This sets the transaction to what the cart in the database is.
            transaction.setId_Account_Foreign(accountUUID);
            transaction.setOrder_Total(confirmedOrder.getTotal());
            List<TransactionItem> transactionItems = transaction.getItems();
            //The loop adds each item from the cart to the transaction items list.
            for(int i = 0; i < confirmedOrder.getItems().size(); ++i)
            {
                TransactionItem transactionItem = new TransactionItem();

                transactionItem.setItemPrice(confirmedOrder.getItems().get(i).getItemPrice());
                transactionItem.setId_Item(confirmedOrder.getItems().get(i).getIdItem());
                transactionItem.setItem_Quantity(confirmedOrder.getItems().get(i).getItemQuantity());
                transactionItem.setTotal(confirmedOrder.getItems().get(i).getTotal());

                transactionItems.add(transactionItem);
            }
            //Once all items are set the items are then stored in the transaction
            transaction.setItems(transactionItems);
            //The old cart is cleared here ready for next use.
            confirmedOrder.getItems().clear();
            confirmedOrder.setTotal(0.00);
            //The cart is then saved as well as the transaction then the transaction is passed back.
            cart_repository.save(confirmedOrder);
            transaction_repository.save(transaction);
        }

        return transaction;

    }
}
