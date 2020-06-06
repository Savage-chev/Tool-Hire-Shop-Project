import java.util.*;

/**
 * @author (Plamen Savchev) 
 * @version (2.5.4 08/03/20)
 * 
 * class <code>Workwear</code> extends class <code>Accessory</code> and hold 
 * the field values specific just for the workwear items
 */
public class Workwear extends Accessory
{
    private String manufacturingStandard;  //standard e.g. EN 812
    
    private String colour;                 //colour e.g. red
    
    private String size;                   //sizee e.g. M
    /**
     * Constructor for objects of class Workwear
     */
    public Workwear() {}
    
    /**
    * Returns the <code>manufacturingStandard</code> field of the <code>Workwear</code> object
    * 
    * @return   the manufacturing standard, as a <code>String</code>
    */
    public String getManufacturingStandard()
    {
        return manufacturingStandard;
    }
    
    /**
    * Returns the <code>colour</code> field of the <code>Workwear</code> object
    * 
    * @return   the colour, as a <code>String</code>
    */
    public String getColour()
    {
        return colour;
    }
    
    /**
    * Returns the <code>size</code> field of the <code>Workwear</code> object
    * 
    * @return   the size, as a <code>String</code>
    */
    public String getSize()
    {
        return size;
    }
    
    /**
    * Assigning values for each <code>Workwear</code>
    * object field from a line of the scanned text file and 

    * @param  <code>scanner2</code> a <code>Scanner</code> object
    */
    public void readData(Scanner scanner2)
    {
        super.readData(scanner2);
        manufacturingStandard = scanner2.next();
        colour = scanner2.next();
        size = scanner2.next();
        scanner2.hasNextLine();
    }
    
    /**
    * Returns all the field values of the <code>Workwear</code> object
    * 
    * @return   the specs of workwear, as a <code>String</code>
    */
    public void printDetails()
    {
        super.printDetails();
        System.out.println("; Manufacturing Standard: " + manufacturingStandard + "; Colour: " + colour
        + "; Size: " + size);
    }
}