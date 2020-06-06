import java.util.*;

/**
 * @author (Plamen Savchev) 
 * @version (2.5.4 08/03/20)
 * 
 * class <code>Accessory</code> extends <code>ShopItem</code> class and simulates
 * accessory and stores some of of the accessory values
 */
public class Accessory extends ShopItem
{
    private boolean isRecyclable;            //is item recycleble
    /**
    * No parameters constructor
    */
    public Accessory() {}

    /**
    * Returns is recyclable of the <code>Accessory</code> object
    * 
    * @return   the isRecyclable, as a <code>boolean</code>
    */
    public boolean isRecyclable()
    {
        return isRecyclable;
    }
    
    /**
    * Assigning values for each <code>Accessory</code> object field from 
    * a line of the scanned text file

    * @param  <code>scanner2</code> a <code>Scanner</code> object
    */
    public void readData(Scanner scanner2)
    {
       isRecyclable = scanner2.nextBoolean();
       super.readData(scanner2);
    }

    /**
    * Returns all the field values of the <code>Accesory</code> object
    * 
    * @return   the specs of accessory, as a <code>String</code>
    */
    public void printDetails()
    {
        System.out.print("Recyclable: "+ 
        isRecyclable);
        super.printDetails();
    }
}