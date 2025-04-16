/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e.commerce;

/**
 *
 * @author dexte
 */
public class CartItem {
    private String name;
    private int price;
    
    
    public CartItem(String name, int price){
        this.name = name;
        this.price =  price;
        
    }
    
    public String getName(){
        return name;
    }
    
    public double getPrice(){
        return price;
    }
    
    
}
