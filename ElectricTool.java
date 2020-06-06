import java.util.*;

/**
 * @author (Plamen Savchev) 
 * @version (2.5.4 08/03/20)
 * 
 * class <code>ElectricTool</code> extends class <code>Tool</code> and hold 
 * the field values specific just for the electric tools
 */
public class ElectricTool extends Tool
{
    private boolean rechargeable;       //is the tool rechargeable
    
    private String power;               //tools power 

    /**
     * Constructor for objects of class ElectricTool
     */
    public ElectricTool() {}

    /**
    * Returns rechargeable of the <code>ElectricTool</code> object
    * 
    * @return   is electric tool rechargeable, as a <code>boolean</code>
    */
    public boolean getRechargeable()
    {
        return rechargeable;
    }
    
    /**
    * Returns the power of the <code>ElectricTool</code> object
    * 
    * @return   the power of electric tool, as a <code>String</code>
    */
    public String getPower()
    {
        return power;
    }
    
    /**
    * Assigning values for each <code>Tool</code> and <code>ElectricTool</code>
    * objects fields from a line of the scanned text file and 

    * @param  <code>scanner2</code> a <code>Scanner</code> object
    */
    public void readData(Scanner scanner2)
    {
        super.readData(scanner2);
        rechargeable = scanner2.nextBoolean();
        power = scanner2.next();
        scanner2.hasNextLine();
    }
    
    /**
    * Returns all the fields value of the <code>Tool</code> object
    * 
    * plus the field value of <code>ElectricTool</code> object
    * 
    * @return   the specs of tool, as a <code>String</code>
    */
    public void printDetails()
    {
        super.printDetails();
        System.out.println("; rechargeable: " + rechargeable + "; power: " + power);
    }
}
