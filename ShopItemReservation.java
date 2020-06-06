import java.util.*;
import java.io.*;
import java.awt.*;

/**
 * @author (Plamen Savchev) 
 * @version (4.2.1 07/06/20)
 * 
 * class <code>ShopItemReservation</code>  stores
 * the reservation values and scanner reading into an <code>HashMap</code>.
 */

public class ShopItemReservation
{
    private String reservationNo;       //number of reservation e.g. 000001
    
    private String itemID;             //ID of the customer e.g. RD2834
    
    private String customerID;         //ID of the customer e.g. AB-167034
    
    private Date startDate;            //date of hire e.g. 22-02-2022
    
    private int noOfDays;              //length of hire e.g. 7 (days)
    /**
     * Parameter constructor for objects of class ShopItemReservation
     */
    public ShopItemReservation(String reservationNo, String itemID, String customerID, String startDate, int noOfDays)
    {
        this.reservationNo = reservationNo;
        this.itemID = itemID;
        this.customerID = customerID;
        this.startDate = DateUtil.convertStringToDate(startDate);
        this.noOfDays = noOfDays;
    }
    
    /**
     * No parameter constructor for objects of class ShopItemReservation
     */
    public ShopItemReservation() {}
    
    /**
    * Returns the <code>reservationNo</code> field value of the <code>ShopItemReservation</code> object
    * 
    * @return   the resrvation number, as a <code>String</code>
    */
    public String getItemReservationNo()
    {
        return reservationNo;
    }
    
    /**
    * Returns the <code>customerID</code> field value of the <code>ShopItemReservation</code> object
    * 
    * @return   the customers ID of certain reservation, as a <code>String</code>
    */
    public String getCustomerID()
    {
        return customerID;
    }
    
    /**
    * Returns the <code>itemID</code> field value of the <code>ShopItemReservation</code> object
    * 
    * @return   the item ID of certain reservation, as a <code>String</code>
    */
    public String getItemID()
    {
        return itemID;
    }
    
    /**
    * Returns the <code>startDate</code> field value of the <code>ShopItemReservation</code> object
    * 
    * @return   the start date of reservation, as a <code>Date</code>
    */
    public Date getStartDate()
    {
        return startDate;
    }
    
    /**
    * Returns the <code>noOfDays</code> field value of the <code>ShopItemReservation</code> object
    * 
    * @return   the length of reservation, as a <code>int</code>
    */
    public int getNoOfDays()
    {
        return noOfDays;
    }
    
    /**
    * Set the <code>reservationNo</code> field value of the <code>ShopItemReservation</code> object
    */
    public void setItemReservationNo(String reservationNo)
    {
        this.reservationNo = reservationNo;
    }
    
    /**
    * Assigning values for each <code>ShopItemReservation</code> object field from 
    * a line of the scanned text file

    * @param  <code>scanner2</code> a <code>Scanner</code> object
    */
    public void readData(Scanner scanner2)
    {
       reservationNo = scanner2.next();
       itemID = scanner2.next();
       customerID = scanner2.next();
       startDate = DateUtil.convertStringToDate(scanner2.next());
       noOfDays = scanner2.nextInt();
    }
    
    /**
    * Writes item reservation data to a file
    * 
    * @param  <code>pWriter</code> a <code>PrintWriter</code> object
    */
    public void writeData(PrintWriter pWriter)
    {
        pWriter.print(reservationNo + ", ");
        pWriter.print(itemID + ", ");
        pWriter.print(customerID + ", ");
        pWriter.print(DateUtil.convertDateToShortString(startDate) + ", ");
        pWriter.print(noOfDays);
        pWriter.println();
    }
    
    /**
    * Returns all the fields value of the <code>ShopItemReservation</code> object
    * 
    * @return   the reservation details, as a <code>String</code>
    */
    public void printDetails()
    {
        System.out.println("Reservation No: " + reservationNo + "; Item ID: " + itemID + "; Customer ID: " + customerID + 
        "; Date of hire: " + startDate + "; Hire duration: " + noOfDays);
    }
    
    /**
    * Returns all the fields values of the <code>ShopItemReservation</code> object
    * 
    * @return   the reservation details, as a <code>String</code>
    */
    public String toString()
    {
        String output = ("Reservation No: " + reservationNo + "; Item ID: " + itemID + "; Customer ID: " + customerID + 
        "; Date of hire: " + startDate + "; Hire duration: " + noOfDays);
        return output;
    }
}