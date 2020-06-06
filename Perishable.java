import java.util.*;

/**
 * @author (Plamen Savchev) 
 * @version (2.5.4 08/03/20)
 * 
 * class <code>Perishable</code> extends class <code>Accessory</code> and hold 
 * the field values specific just for the perishable items
 */
public class Perishable extends Accessory
{
    private boolean isIrritant;       //boolean return if item is irritant
    
    private String useByDate;         //use by date e.g. 17-02-2019
    
    private int volume;               //current volume e.g. 250
    /**
     * Constructor for objects of class Perishable
     */
    public Perishable() {}

    /**
    * Returns <code>isIttitant</code> field of the <code>Perishable</code> object
    * 
    * @return   if parishable is irritant, as a <code>boolean</code>
    */
    public boolean getIsIrritant()
    {
        return isIrritant;
    }
    
    /**
    * Returns the <code>useByDate</code> field of the <code>Perishable</code> object
    * 
    * @return   the use-by-date, as a <code>String</code>
    */
    public String getUseByDate()
    {
        return useByDate;
    }
    
    /**
    * Returns the <code>volume</code> field of the <code>Perishable</code> object
    * 
    * @return   the volume, as a <code>int</code>
    */
    public int getVolume()
    {
        return volume;
    }
    
    /**
    * Assigning values for each <code>Tool</code> and <code>HandTool</code>
    * objects fields from a line of the scanned text file and 

    * @param  <code>scanner2</code> a <code>Scanner</code> object
    */
    public void readData(Scanner scanner2)
    {
        super.readData(scanner2);
        isIrritant = scanner2.nextBoolean();
        useByDate = scanner2.next();
        volume = scanner2.nextInt();
        scanner2.hasNextLine();
    }
    
    /**
    * Returns all the field values of the <code>Perishable</code> object
    * 
    * @return   the specs of perishables, as a <code>String</code>
    */
    public void printDetails()
    {
        super.printDetails();
        System.out.println("; Irritant: " + isIrritant + "; Use-By-Date: " + useByDate +
        "; Volume: " + volume);
    }
}