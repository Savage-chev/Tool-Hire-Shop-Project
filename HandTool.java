import java.util.*;

/**
 * @author (Plamen Savchev) 
 * @version (2.5.4 08/03/20)
 * 
 * class <code>HandTool</code> extends class <code>Tool</code> and hold 
 * the field values specific just for the hand tools
 */
public class HandTool extends Tool
{
    private boolean sharpenable;       //is tool able to be sharpened 

    /**
     * Constructor for objects of class HandTool
     */
    public HandTool() {}

    /**
    * Returns sharpenable of the <code>HandTool</code> object
    * 
    * @return   is hand tool sharpenable, as a <code>boolean</code>
    */
    public boolean getSharpenable()
    {
        return sharpenable;
    }
    
    /**
    * Assigning values for each <code>Tool</code> and <code>HandTool</code>
    * objects fields from a line of the scanned text file and 

    * @param  <code>scanner2</code> a <code>Scanner</code> object
    */
    public void readData(Scanner scanner2)
    {
        super.readData(scanner2);
        sharpenable = scanner2.nextBoolean();
        scanner2.hasNextLine();
    }
    
    /**
    * Returns all the field values of the <code>Tool</code> object
    * 
    * plus the fields values of <code>HandTool</code> object
    * 
    * @return   the specs of tool, as a <code>String</code>
    */
    public void printDetails()
    {
        super.printDetails();
        System.out.println("; sharpenable: " + sharpenable);
    }
}
