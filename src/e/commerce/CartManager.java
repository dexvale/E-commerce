/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e.commerce;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dexte
 */
public class CartManager {
    
    private List<CartItem> cartItems;
    
    public CartManager(){
        cartItems = new ArrayList<>();
    }
    public void additem(CartItem item){
        cartItems.add(item);
        System.out.println(item.getName() + " added to cart.");
                
    }
    
    public List<CartItem> getItems(){
        return cartItems;
    }
    
    public void clearCart(){
        cartItems.clear();
    }
        
    
    
}
