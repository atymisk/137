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
public class CartItem 
{
    private Integer PID;// retrieve from DB
    private String productname;
    private Integer price;
    private Integer quantity;
    private Integer totalprice;
    
    public CartItem(Integer id,String s, Integer i, Integer i2)
    {
        PID = id;
        productname = s;
        price = i;
        quantity = i2;
        totalprice = price * quantity;
    }
    
    public Integer getPID()
    {
        return PID;
    }
    
    public String getName()
    {
        return productname;
    }
    
    public Integer getPrice()
    {
        return price;
    }
    
    public Integer getQuantity()
    {
        return quantity;
    }
    
    public void SetQuantity(Integer i)
    {
        quantity = i;
        totalprice = quantity * price;
    }
    
    public void UpdateQuantity(Integer i)
    {
        quantity += i;
        totalprice = quantity * price;
    }
    
    public Integer getTotalPrice()
    {
        return totalprice;
    }
}
