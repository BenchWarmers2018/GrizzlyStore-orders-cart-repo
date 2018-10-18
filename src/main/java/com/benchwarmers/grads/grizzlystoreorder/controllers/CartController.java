package com.benchwarmers.grads.grizzlystoreorder.controllers;

import com.benchwarmers.grads.grizzlystoreorder.entities.Cart;
import com.benchwarmers.grads.grizzlystoreorder.entities.CartItem;
import com.benchwarmers.grads.grizzlystoreorder.repositories.CartItem_Repository;
import com.benchwarmers.grads.grizzlystoreorder.repositories.Cart_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(path = "/cart")
public class CartController
{

    @Autowired
    Cart_Repository cart_repository;

    @Autowired
    CartItem_Repository cartItem_repository;

    //This gets all items in the cart or creates a new cart for the user if they do not have one.
    @RequestMapping(path = "/items", method = RequestMethod.POST)
    public Cart getCart(@RequestBody Cart cart)
    {
        //Grabs the id sent in and converts it to UUID
        UUID accountUUID = cart.getIdAccountForeign();
        //Creates new cart object.
        Cart cartInDatabase = new Cart();
        //Checks if a cart exists if it does it fetches it else creates a new cart and returns it.
        if (cart_repository.existsByIdAccountForeign(accountUUID))
        {
            cartInDatabase = cart_repository.findCartByIdAccountForeign(accountUUID);
        }
        else
        {
            cartInDatabase.setIdAccountForeign(accountUUID);
            cart_repository.save(cartInDatabase);
        }

        return cartInDatabase;
    }
    //This adds an item to an existing cart.
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Cart addToCart(@RequestBody Cart cart)
    {
        UUID accountUUID = cart.getIdAccountForeign();
        Cart cartInDatabase = new Cart();
        double cartTotal = 0.0;
        double cartItemTotal = 0.0;
        boolean itemExists = false;
        //Checks if the cart exists and checks if the same item is added again just to combine the quantity and recalculate
        if (cart_repository.existsByIdAccountForeign(accountUUID))
        {
            //Sets cartInDatabase to grab a cart from the database based on the id given
            cartInDatabase = cart_repository.findCartByIdAccountForeign(accountUUID);
            //Calculates the total of the cart item given so it can be added or removed later
            cartItemTotal = cart.getItems().get(0).getItemQuantity() * cart.getItems().get(0).getItemPrice();

            //This look checks the cartInDatabase if an item that's being added already exists and if it does add the quantity
            for(int i  = 0; i < cartInDatabase.getItems().size(); ++i)
            {
                if(cartInDatabase.getItems().get(i).getIdItem().equals(cart.getItems().get(0).getIdItem()))
                {
                    cartInDatabase.getItems().get(i).setItemQuantity(cartInDatabase.getItems().get(i).getItemQuantity() + cart.getItems().get(0).getItemQuantity());
                    //This gets set to true if the item added exists already.
                    itemExists = true;
                    cartInDatabase.getItems().get(i).setTotal(cartInDatabase.getItems().get(i).getTotal()+ cartItemTotal);
                    //cartTotal = cartInDatabase.getItems().get(i).getTotal();
                }
            }
            //If the item doesn't exist then add it to the cart.
            if(!itemExists){
                cart.getItems().get(0).setTotal(cartItemTotal);
                cartInDatabase.addItem(cart.getItems().get(0));
                //cartTotal = (cart.getItems().get(0).getItemPrice() * cart.getItems().get(0).getItemQuantity());
            }

            cartInDatabase.setTotal(cartInDatabase.getTotal() + cartItemTotal);

            cart_repository.save(cartInDatabase);
        }
        else
        {
            //If a cart doesn't belong to an Account then create a new cart
            cartInDatabase.setIdAccountForeign(accountUUID);
            cartItemTotal = cart.getItems().get(0).getItemQuantity() * cart.getItems().get(0).getItemPrice();
            cart.getItems().get(0).setTotal(cartItemTotal);
            cartInDatabase.addItem(cart.getItems().get(0));
            cartTotal = (cart.getItems().get(0).getItemPrice()*cart.getItems().get(0).getItemQuantity());
            cartInDatabase.setTotal(cartInDatabase.getTotal() + cartTotal);
            cart_repository.save(cartInDatabase);
        }

        return cart_repository.findCartByIdAccountForeign(accountUUID);
    }
    //Updates the cart with new totals based on the quantities.
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public Cart updateCart(@RequestBody Cart cart)
    {
        UUID accountUUID = cart.getIdAccountForeign();
        Cart cartInDatabase = new Cart();

        if (cart_repository.existsByIdAccountForeign(accountUUID))
        {
            cartInDatabase = cart_repository.findCartByIdAccountForeign(accountUUID);
            List<CartItem> items = cartInDatabase.getItems();
            List<CartItem> items2 = cart.getItems();


            for(int i = 0; i <items2.size(); ++i)
            {
                for(int j = 0; j < items.size(); ++j)
                {
                    if (items.get(j).getIdItem().equals(cart.getItems().get(i).getIdItem()))
                    {
                        //This minus's the previous cart total
                        cartInDatabase.setTotal(cartInDatabase.getTotal() - cartInDatabase.getItems().get(j).getTotal());
                        //This then sets the cart quantity based on the new values passed.
                        cartInDatabase.getItems().get(j).setItemQuantity(cart.getItems().get(i).getItemQuantity());
                        //This then sets each items total
                        cartInDatabase.getItems().get(j).setTotal(cartInDatabase.getItems().get(j).getItemPrice() * cartInDatabase.getItems().get(j).getItemQuantity());
                        //This then adds the the cart items total.
                        cartInDatabase.setTotal(cartInDatabase.getTotal() + (cartInDatabase.getItems().get(j).getItemPrice() * cart.getItems().get(i).getItemQuantity()));

                    }
                }
            }
            cart_repository.save(cartInDatabase);
            return cart_repository.findCartByIdAccountForeign(accountUUID);
        }
        return cartInDatabase;
    }
    //Deletes item from carts
    @RequestMapping(path = "/deleteitem", method = RequestMethod.DELETE)
    public Cart deleteFromCart(@RequestBody Cart cart)
    {
        UUID accountUUID = cart.getIdAccountForeign();
        Cart cartInDatabase = new Cart();
        if (cart_repository.existsByIdAccountForeign(accountUUID))
        {
            cartInDatabase = cart_repository.findCartByIdAccountForeign(accountUUID);
            List<CartItem> items = cartInDatabase.getItems();
            if(items.size() > 0)
            {
                cartInDatabase.getItems().remove(items.get(0));
                cartInDatabase.setTotal(cartInDatabase.getTotal() - cart.getItems().get(0).getTotal());
            }
        }

        cart_repository.save(cartInDatabase);
        return cartInDatabase;
    }
    //Deletes the entire cart
    @RequestMapping(path = "/deletecart", method = RequestMethod.DELETE)
    public Cart deleteWholeCart(@RequestBody Cart cart)
    {

        UUID accountUUID = cart.getIdAccountForeign();
        Cart cartInDatabase = cart_repository.findCartByIdAccountForeign(accountUUID);
        //Clears the cart so all items are removed but the actual cart is not removed.
        cartInDatabase.getItems().clear();
        //The total of the cart is then set to 0.00 and saved.
        cartInDatabase.setTotal(0.00);
        cart_repository.save(cartInDatabase);
        return cartInDatabase;
    }
}
