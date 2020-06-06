import java.util.*;

/**
 * @author (Plamen Savchev) 
 * @version (2.5.4 08/03/20)
 * 
 * class <code>Tool</code> extends <code>ShopItem</code> class and simulates
 * tool and stores some of of the tool values
 */
public class Tool extends ShopItem
{
    private int timesBorrowed;         //how many times item been borrowed
    
    private boolean onLoan;            //is item currently available
    
    private int weight;                //weight in grammes
    /**
    * No parameters constructor
    */
    public Tool() {}

    /**
    * Returns the times <code>Tool</code> object been borrowed
    * 
    * @return   the how many times tool been borrowed, as a <code>int</code>
    */
    public int getTimesBorrowed()
    {
        return timesBorrowed;
    }
    
    /**
    * Returns the availability of the <code>Tool</code> object
    * 
    * @return   the availability, as a <code>String</code>
    */
    public String getOnLoan()
    {
        String msg;
        if (onLoan == true)
        {
            msg = "Yes";
        }
        else
        {
            msg = "No";
        }
        return msg;
    }
    
    /**
    * Returns the availability of the <code>Tool</code> object
    * 
    * @return   the availability, as a <code>boolean</code>
    */
    public boolean getOnLoan1()
    {
        return onLoan;
    }
    
    /**
    * Returns the weight of the <code>Tool</code> object
    * 
    * @return   the weight of tool, as a <code>int</code>
    */
    public int getWeight()
    {
        return weight;
    }
    
    /**
    * Assigning values for each <code>Tool</code> object field from 
    * a line of the scanned text file

    * @param  <code>scanner2</code> a <code>Scanner</code> object
    */
    public void readData(Scanner scanner2)
    {
       super.readData(scanner2);
       timesBorrowed = scanner2.nextInt();
       onLoan = scanner2.nextBoolean();
       weight = scanner2.nextInt();
    }

    /**
    * Returns all the field values of the <code>Tool</code> object
    * 
    * @return   the specs of tool, as a <code>String</code>
    */
    public void printDetails()
    {
        super.printDetails();
        System.out.print("; timesBorrowed: " + timesBorrowed + "; onLoan: "+ 
        onLoan + "; weight: " + weight);
    }
}
