import java.util.*;
import java.io.*;
import java.awt.*;

/**
 * @author (Plamen Savchev) 
 * @version (2.5.4 08/03/20)
 * 
 * class <code>ShopItem</code>  stores
 * the item values and scanner reading into an <code>ArrayList</code>.
 */
public class ShopItem
{
    private String itemName;           //name of the item e.g. DeWalt Circular Saw
    
    private String itemCode;           //id number e.g. RD6582
    
    private int cost;                  //price of the item when new
    /**
     * Constructor for objects of class ShopItem
     */
    public ShopItem() {}

    /**
    * Returns the <code>itemName</code> field value of the <code>ShopItem</code> object
    * 
    * @return   the name of item, as a <code>String</code>
    */
    public String getItemName()
    {
        return itemName;
    }
    
    /**
    * Returns the <code>itemCode</code> field value of the <code>ShopItem</code> object
    * 
    * @return   the id number of item, as a <code>String</code>
    */
    public String getItemCode()
    {
        return itemCode;
    }
    
    /**
    * Returns the <code>cost</code> field value of the <code>ShopItem</code> object
    * 
    * @return   the hire cost of tool, as a <code>int</code>
    */
    public int getCost()
    {
        return cost;
    }
    
    /**
    * Assigning values for each <code>ShopItem</code> object field from 
    * a line of the scanned text file

    * @param  <code>scanner2</code> a <code>Scanner</code> object
    */
    public void readData(Scanner scanner2)
    {
       itemName = scanner2.next();
       itemCode = scanner2.next();
       cost = scanner2.nextInt();
    }
    
    /**
    * Returns all the fields value of the <code>ShopItem</code> object
    * 
    * @return   the specs of item, as a <code>String</code>
    */
    public void printDetails()
    {
        System.out.print(" Item name: " + itemName + "; Code: " + itemCode +"; Cost: " + cost);
    }
}
