/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionObjects;

/**
 *
 * @author ATY
 */
import java.util.HashMap;

public class Cart 
{
    private HashMap<String, CartItem> cartItems;
    public Cart()
    {
        cartItems = new HashMap<>();
    }
    public HashMap<String,CartItem> getCartItems()
    {
        return cartItems;
    }
    public void addToCart(int PID, String itemId, int price, int quantity)
    {
        if(cartItems.containsKey(itemId))
        {
            cartItems.get(itemId).UpdateQuantity(quantity);
        }
        else
        {
            cartItems.put(itemId, new CartItem(PID, itemId, price, quantity));
        }
    }
    
    public void updateCart(String itemId, int qty)
    {
        if(qty == 0)
        {
            cartItems.remove(itemId);
        }
        else
        {
            cartItems.get(itemId).SetQuantity(qty);
        }
    }
    
    public void deleteFromCart(String itemId)
    {
        cartItems.remove(itemId);
    }
     
    public void clearCart()
    {
        cartItems.clear();
    }
}