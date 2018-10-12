package com.benchwarmers.grads.grizzlystoreorder.controllers;

import com.benchwarmers.grads.grizzlystoreorder.entities.Cart;
import com.benchwarmers.grads.grizzlystoreorder.entities.CartItem;
import com.benchwarmers.grads.grizzlystoreorder.repositories.Cart_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(path = "/cart")
public class CartController
{

    @Autowired
    Cart_Repository cart_repository;

    //This gets all items in the cart or creates a new cart for the user if they do not have one.
    @RequestMapping(path = "/items", method = RequestMethod.POST)
    public Cart getCart(@RequestBody Cart cart)
    {
        //Grabs the id sent in and converts it to UUID
        UUID accountUUID = cart.getIdAccountForeign();
        //Creates new cart object.
        Cart newCart = new Cart();
        //Checks if a cart exists if it does it fetches it else creates a new cart and returns it.
        if (cart_repository.existsByIdAccountForeign(accountUUID))
        {
            newCart = cart_repository.findCartByIdAccountForeign(accountUUID);
        }
        else
        {
            newCart.setIdAccountForeign(accountUUID);
            cart_repository.save(newCart);
        }

        return newCart;
    }
    //This adds an item to an existing cart.
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Cart addToCart(@RequestBody Cart cart)
    {
        UUID accountUUID = cart.getIdAccountForeign();
        Cart newCart = new Cart();
        if (cart_repository.existsByIdAccountForeign(accountUUID))
        {
            newCart = cart_repository.findCartByIdAccountForeign(accountUUID);
            newCart.addItem(cart.getItems().get(0));
            cart_repository.save(newCart);
        }

        return newCart;
    }
    //Edit Quantity of an existing item in a cart.
    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public Cart editCart(@RequestParam String id, @RequestBody CartItem cartItem, @RequestParam int quantity)
    {
        UUID accountUUID = UUID.fromString(id);
        Cart cart = cart_repository.findCartByIdAccountForeign(accountUUID);
        return cart;
    }
    //Deletes item from carts
    @RequestMapping(path = "/deleteitem", method = RequestMethod.POST)
    public Cart deleteFromCart(@RequestBody Cart cart)
    {
        UUID accountUUID = cart.getIdAccountForeign();
        Cart newCart = new Cart();
        if (cart_repository.existsByIdAccountForeign(accountUUID))
        {
            newCart = cart_repository.findCartByIdAccountForeign(accountUUID);
            newCart.removeItem(cart.getItems().get(0));
            cart_repository.save(newCart);
        }

        return newCart;
    }
    //Deletes the entire cart
    @RequestMapping(path = "/deletecart", method = RequestMethod.POST)
    public Cart deleteWholeCart(@RequestParam String id, @RequestBody CartItem cartItem)
    {
        UUID accountUUID = UUID.fromString(id);
        Cart cart = cart_repository.findCartByIdAccountForeign(accountUUID);
        return cart;
    }
}
