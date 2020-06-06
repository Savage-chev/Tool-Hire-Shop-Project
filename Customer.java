import java.util.*;
import java.io.*;
import java.awt.*;

/**
 * @author (Plamen Savchev) 
 * @version (6.5 03/04/20)
 * 
 * class <code>Customer</code>  stores
 * the customer values and scanner reading into an <code>HashMap</code>.
 */

public class Customer
{
    private String customerID;        //Id of the customer e.g. unknown
    
    private String surname;           //surname of the customer e.g. Newton
    
    private String firstName;         //first name of the customer e.g. David
    
    private String otherInitials;     //other initials of the customer e.g. E
    
    private String title;             //title of the customer e.g. Dr
    /**
     * Parameter constructor for objects of class Customer
     */
    public Customer(String surname, String firstName, String otherInitials, String title)
    {
        customerID = "unknown";
        this.surname = surname;
        this.firstName = firstName;
        this.otherInitials = otherInitials;
        this.title = title;
    }
    
    /**
     * No parameter constructor for objects of class Customer
     */
    public Customer() {}
    
    /**
    * Returns the <code>customerID</code> field value of the <code>Customer</code> object
    * 
    * @return   the ID of customer, as a <code>String</code>
    */
    public String getCustomerID()
    {
        return customerID;
    }
    
    /**
    * Returns the <code>surname</code> field value of the <code>Customer</code> object
    * 
    * @return   the surname of customer, as a <code>String</code>
    */
    public String getSurname()
    {
        return surname;
    }
    
    /**
    * Returns the <code>firstName</code> field value of the <code>Customer</code> object
    * 
    * @return   the first name of customer, as a <code>String</code>
    */
    public String getFirstName()
    {
        return firstName;
    }
    
    /**
    * Returns the <code>otherInitials</code> field value of the <code>Customer</code> object
    * 
    * @return   the other initials of customer, as a <code>String</code>
    */
    public String getOtherInitials()
    {
        return otherInitials;
    }
    
    /**
    * Returns the <code>title</code> field value of the <code>Customer</code> object
    * 
    * @return   the title of customer, as a <code>String</code>
    */
    public String getTitle()
    {
        return title;
    }
    
    /**
    * Set the <code>customerID</code> field value of the <code>Customer</code> object
    */
    public void setCustomerID(String customerID)
    {
        this.customerID = customerID;
    }
    
    /**
    * Assigning values for each <code>Customer</code> object field from 
    * a line of the scanned text file

    * @param  <code>scanner2</code> a <code>Scanner</code> object
    */
    public void readData(Scanner scanner2)
    {
       customerID = scanner2.next();
       surname = scanner2.next();
       firstName = scanner2.next();
       otherInitials = scanner2.next();
       title = scanner2.next();
    }
    
    /**
    * Writes customer data to a file
    * 
    * @param  <code>pWriter</code> a <code>PrintWriter</code> object
    */
    public void writeData(PrintWriter pWriter)
    {
            pWriter.print(customerID + ", ");
            pWriter.print(surname + ", ");
            pWriter.print(firstName + ", ");
            pWriter.print(otherInitials + ", ");
            pWriter.print(title);
            pWriter.println();
    }
    
    /**
    * Returns all the fields value of the <code>Customer</code> object
    * 
    * @return   the customer details, as a <code>String</code>
    */
    public void printDetails()
    {
        System.out.println("CustomerID: " + customerID + "; Surname: " + surname +"; First Name: " + firstName +
        "; Other Initials: " + otherInitials + "; Title: " + title);
    }
}
