import java.util.*;
import java.io.*;
import java.awt.*;
import java.util.Map.Entry;

/**
 * @author (Plamen Savchev) 
 * @version (4.5.3 11/05/20)
 * 
 * class <code>Shop</code> simulates shop and stores the items, customers and reservations
 * of the shop in <code>HashMap</code>.
 */
public class Shop
{
    private Map<String, ShopItem> itemMap;                       //HashMap to store the items objects
    //private List<ShopItem> itemList; 
    
    private Map<String, Customer> customerMap;                   //HashMap list to store the customer objects
    //private List<Customer> customerList;   
    
    private Map<String, ShopItemReservation> itemReservationMap; //HashMap to store the reservations objects
    
    private Diary diary;                                         //Class diary stores Diary hash map and prcess reservations
    
    private String dumpCustomerDataFileName;                     //Hold the name of the file where Customer date will be saved
    
    private String dumpItemReservationDataFileName;              //Hold the name of the file where Item Reservation date will be saved
    /**
     * Constructor for objects of class Shop
     * 
     * @param <code>shopName</code> a <code>String</code> object.Name of the shop.
     */
    public Shop(String shopName)
    {
        itemMap = new HashMap<String, ShopItem>();
        //itemList = new ArrayList<ShopItem>();
        
        customerMap = new HashMap<String, Customer>();
        //customerList = new ArrayList<Customer>();
        
        itemReservationMap = new HashMap<String, ShopItemReservation>();
        
        diary = new Diary();
        
        readItemData("items_all.txt");
        
        dumpCustomerDataFileName = shopName + "_customer_dump.txt";
        
        dumpItemReservationDataFileName = shopName + "_shop_item_reservation_dump.txt";
    }
    
    /**
     * Method allowing the system to load all the information when system is open.
     * Reads and fills the <code>customerMap</code> and <code>itemReservationMap</code> from text
     * files named as the <Code>String</code>s hold in <code>dumpCustomerDataFileName</code>
     * and <code>dumpItemReservationDataFileName</code> respectively.
     */
    public void reloadSystem()
    {
        File file = null;
        file = new File(dumpCustomerDataFileName);
        if (file.exists())
        {
            file = null;
            readCustomerData(dumpCustomerDataFileName);
        }
        else
        {
            System.out.println("Customer data file does not exist!");
        }
        file = new File(dumpItemReservationDataFileName);
        if (file.exists())
        {
            file = null;
            readItemReservationData(dumpItemReservationDataFileName);
        }
        else
        {
            System.out.println("Item reservation data file does not exist!");
        }
    }
    
    /**
     * Method allowing the system to save all the information when system is closed.
     * Write down the <code>customerMap</code> and <code>itemReservationMap</code> as text
     * files named as the <Code>String</code> hold in <code>dumpCustomerDataFileName</code>
     * and <code>dumpItemReservationDataFileName</code> respectively.
     */
    public void closeDownSystem()
    {
        writeCustomerData(dumpCustomerDataFileName);
        writeItemReservationData(dumpItemReservationDataFileName);
    }
    
    /**
     *Method check if all parameters are valid. If so generates new reservation id creates new object from <code>ShopItemReservation</code>
     *and by call to <code>storeItemReservation</code> store in the <code>itemReservationMap</code> and <code>dayInDiaryMap</code>
     *creates new obj
     *
     * @param   <code>itemID</code> an <code>String</code> should exist in itemMap
     * @param   <code>customerID</code> an <code>String</code> should exist in customerMap
     * @param   <code>startDate</code> an <code>String</code> date of the hire
     * @param   <code>noOfDays</code> an <code>Integer</code> duration of hire
     */
    public boolean makeItemReservation(String itemID, String customerID, String startDate, int noOfDays)
    {
        boolean check = true;
        String msg = "";
        if (customerMap.containsKey(customerID))
        {
            if (itemMap.containsKey(itemID))
            {
                boolean hired = false;
                if (!itemReservationMap.isEmpty())
                {
                    for (ShopItemReservation itemReservation: itemReservationMap.values())
                    {
                        if (itemReservation.getItemID().equals(itemID))
                        {
                            int counter = 0;
                            for (int i =0; i<noOfDays; i++)
                            {
                                Date day = DateUtil.incrementDate(DateUtil.convertStringToDate(startDate), i);
                                ShopItemReservation[] dayOfRes = diary.getReservations(day);
                                hired = true;
                                if (hired = true) 
                                {
                                    counter +=1;
                                }
                                if (counter > 0)
                                {
                                    msg ="The item will be hired for "+counter+" days of your desired period.\n The item is on hire from "+
                                    itemReservation.getStartDate()+" for the period of "+itemReservation.getNoOfDays()+
                                    "days.\n Please choose another date after that period!";
                                }
                            }
                        }
                    }
                }
                if (hired = false)
                {
                    if (startDate.matches("^[0-3][0-9]-[0-1][0-9]-[0-9]{2}$"))
                    {
                        if (noOfDays > 0)
                        {
                            String resNo = generateReservationNo();
                            while (itemReservationMap.containsKey(resNo))
                            {
                                generateReservationNo();
                            }
                            ShopItemReservation itemReservation = new ShopItemReservation(resNo, itemID, customerID, startDate,noOfDays);
                            storeItemReservation(itemReservation);
                            msg = "Reservation Successfully Added!";
                        }
                        else
                        {
                            msg = "Invalid hire duration!";
                            check = false;
                        }
                    }
                    else
                    {
                        msg = "Invalid start date!";
                        check = false;
                    }
                }
            }
            else
            {
                msg = "Invalid item ID!";
                check = false;
            }
        }
        else
        {
            msg = "Invalid customer ID!";
            check = false;
        }
        System.out.println(msg);
        return check;
    }
    
    /**
     * Returns the size of the <code>itemMap</code> of <code>ShopItem</code> objects.
     */
    public int getNumberOfItems()
    {
        return itemMap.size();
    }
    
    /**
     * Returns the size of the <code>customerMap</code> of <code>Customer</code> objects.
     */
    public int getNumberOfCustomers()
    {
        return customerMap.size();
    }
    
    /**
     * Returns the size of the <code>itemReservationMap</code> of <code>ShopItemReservation</code> objects.
     */
    public int getNumberOfReservations()
    {
        return itemReservationMap.size();
    }
    
    /**
     * Adds an item to the itemMap
     *
     * @param   <code>item</code> an <code>ShopItem</code> object, the 
     * item to be added
     */
    private void storeItem(ShopItem item)
    {
        itemMap.put(item.getItemCode(), item);
        //itemList.add(item);
    }
    
    /**
     * Adds an customer to the customerMap
     *
     * @param   <code>customer</code> an <code>Customer</code> object, the 
     * customer to be added
     */
    private void storeCustomer(Customer customer)
    {
        if (customer.getCustomerID() == null || customer.getCustomerID().equals("unknown"))
        {
            String newID = generateCustomerID("AB-", 6);
            while (customerMap.containsKey(newID))
            {
                generateCustomerID("AB-", 6);
            }
            customer.setCustomerID(newID);
        }
        else if (customerMap.containsKey(customer.getCustomerID()))
        {
            System.out.println("Customer has been added already!");
        }
        customerMap.put(customer.getCustomerID(), customer);
        //if (customer.getCustomerID() == null || customer.getCustomerID().equals("unknown"))
        //{
        //    generateCustomerID("AB-", 6);
        //    String newCustomerID = customer.getCustomerID();
        //    
        //    Iterator<Customer> it = customerList.iterator();
        //   while (it.hasNext())
        //    {
        //        Customer currentCustomer = it.next();
        //        String currentCustomerID = currentCustomer.getCustomerID();
        //        if (currentCustomerID.equals(newCustomerID))
        //            generateCustomerID("AB-", 6);
        //    }
        //}
        //else
        //{
        //    System.out.println("Customer have ID already!");
        //}
        //customerList.add(customer);
    }
    
    /**
     * Adds an item reservation to the itemReservationMap
     *
     * @param   <code>itemReservation</code> an <code>ShopItemReservation</code> object, the 
     * item reservation to be added
     */
    private void storeItemReservation(ShopItemReservation itemReservation)
    {
        itemReservationMap.put(itemReservation.getItemReservationNo(), itemReservation);
        diary.addReservation(itemReservation);
    }
    
    /**
    * Generates the <code>customerID</code> field value of the <code>Customer</code> object
    */
    private String generateCustomerID(String prefix, int length)
    {
        Random rnd = new Random();
        String customerID = prefix;
        
        for (int i=0; i<length; i++) {
            customerID += rnd.nextInt(10);
        }
        return customerID;
    }
    
    /**
    * Generates the <code>reservationNo</code> field value of the <code>ShopItemReservation</code> object
    */
    private String generateReservationNo()
    {
        Random rnd = new Random();
        String ResNo = "";
        
        for (int i=0; i<6; i++) {
            ResNo += rnd.nextInt(10);
        }
        return ResNo;
    }
    
    /**
     * Get item by its key <code>itemCode</code>
     *
     * @param   <code>item</code> an <code>ShopItem</code> object, the 
     * item to be found by its key
     */
    public ShopItem getItem(String itemCode)
    {
        if (itemMap.containsKey(itemCode))
        {
            ShopItem item = itemMap.get(itemCode);
            return item;
        }
        else
        {
            System.out.println("Item Code does not exists!");
            return null;
        }
    }
    
    /**
     * Get customer by its key <code>customerID</code>
     *
     * @param   <code>customer</code> an <code>Customer</code> object, the 
     * item to be found by its key
     */
    public Customer getCustomer(String customerID)
    {
        if (customerMap.containsKey(customerID))
        {
            Customer customer = customerMap.get(customerID);
            return customer;
        }
        else
        {
            System.out.println("Customer ID does not exists!");
            return null;
        }
    }
    
    /**
     * Get reservation by its key <code>reservationNo</code>
     *
     * @param   <code>reservation</code> an <code>ShopItemReservation</code> object, the 
     * reservation to be found by its key
     */
    public ShopItemReservation getItemReservation(String reservationNo)
    {
        if (itemReservationMap.containsKey(reservationNo))
        {
            ShopItemReservation reservation = itemReservationMap.get(reservationNo);
            return reservation;
        }
        else
        {
            System.out.println("Reservation No does not exists!");
            return null;
        }
    }
    
    /**
     * Remove all items from itemMap
     */
    public void clearItemMap()
    {
        itemMap.clear();
        //itemList.clear();
        System.out.println("All items successfully removed!");
    }
    
    /**
     * Remove all customers from customerMap
     */
    public void clearCustomerMap()
    {
        customerMap.clear();
        //customerList.clear();
        System.out.println("All customers successfully removed!");
    }

    /**
     * Remove all reservations from itemReservationMap
     */
    public void clearReservationMap()
    {
        itemReservationMap.clear();
        System.out.println("All reservations successfully removed!");
    }
    
    /**
     * Remove item from the itemMap
     *
     */
    public void removeItem(String itemCode)
    {
        if ( itemMap.containsKey(itemCode) )
        {
            itemMap.remove(itemCode);
            System.out.println("Item successfully removed!");
            printItemDetails();
        }
        else
        {
            System.out.println("Invalid item code!");
            System.out.println("Please enter valid code!");
        }
    }
    
    //public void removeItem(int listPosition)
    //{
    //    listPosition -= 1;
    //    if (listPosition >= 0 && listPosition < itemList.size() )
    //    {
    //        itemList.remove(listPosition);
    //        System.out.println("Item successfully removed!");
    //        printAllItems();
    //    }
    //    else
    //    {
    //        System.out.println("Invalid Position!");
    //        System.out.println("Please enter valid position!");
    //    }
    //}
    
    /**
     * Remove customer from the customerMap
     *
     */
    public void removeCustomer(String customerID)
    {
        if ( customerMap.containsKey(customerID) )
        {
            customerMap.remove(customerID);
            System.out.println("Customer successfully removed!");
            printCustomerDetails();
        }
        else
        {
            System.out.println("Invalid customer ID!");
            System.out.println("Please enter valid customer ID!");
        }
    }
    
    //public void removeCustomer(int listPosition)
    //{
    //    listPosition -= 1;
    //    if (listPosition >= 0 && listPosition < itemList.size() )
    //    {
    //        customerList.remove(listPosition);
    //        System.out.println("Customer successfully removed!");
    //        printAllCustomers();
    //    }
    //    else
    //    {
    //        System.out.println("Invalid Position!");
    //        System.out.println("Please enter valid position!");
    //    }
    //}

    /**
     * Remove reservation from the itemReservationMap
     *
     */
    public void deleteItemReservation(String reservationNo)
    {
        if ( itemReservationMap.containsKey(reservationNo) )
        {
            diary.deleteReservation(getItemReservation(reservationNo));
            itemReservationMap.remove(reservationNo);
            System.out.println("Reservation successfully removed!");
            printItemReservation();
        }
        else
        {
            System.out.println("Invalid reservation No!");
            System.out.println("Please enter valid reservation No!");
        }
    }
    
    /**
     * Printing the details of all items object from the itemMap.  
     */
    public void printItemDetails()
    {
        //if (itemList.size() != 0)
        if (itemMap.size() != 0)
        {
            //for (ShopItem item: itemList)
            for (ShopItem item: itemMap.values())
            {
                item.printDetails();
            }
        }
        else
        {
            System.out.println("Item list is empty!");
        }
    }
    
    /**
     * Printing the details of all customer objects from the customerMap.  
     */
    public void printCustomerDetails()
    {
        //if (customerList.size() != 0)
        if (customerMap.size() != 0)
        {
            //for (Customer customer: customerList)
            for (Customer customer: customerMap.values())
            {
                customer.printDetails();
            }
        }
        else
        {
            System.out.println("Customer list is empty!");
        }
    }

    /**
     * Printing the details of all reservation objects from the customerMap.  
     */
    public void printItemReservation()
    {
        if (itemReservationMap.size() != 0)
        {
            for (ShopItemReservation itemReservation: itemReservationMap.values())
            {
                itemReservation.printDetails();
            }
        }
        else
        {
            System.out.println("Reservation list is empty!");
        }
    }
    
    /**
     * Printing the details of the <code>Diary</code> object.  
     */
    public void printDiaryEntries(String startDate, String endDate)
    {
        if (diary != null)
        {
            diary.printEntries(DateUtil.convertStringToDate(startDate), DateUtil.convertStringToDate(endDate));
        }
    }
    
    /**
     * Opens <code>fileName</code> a <code>FileDialog</code> object with an absolute file path.
     * The open file is passed to the <code>writeCustomerData()</code> as parameter.
     */ 
    public void writeCustomerData()
    {
        Frame myFrame = new Frame();
        FileDialog fileName = new FileDialog (myFrame,"Open", FileDialog.LOAD);
        fileName.setVisible(true);
        if (fileName !=null) {
            String filePath = fileName.getDirectory() + fileName.getFile();
        }
        writeCustomerData(fileName.getFile());
    }
    
    /**
     * Writes customer data to a file
     * 
     * @parem  <code>fileName</code> a <code>String</code> the name of
     *         the text file in which the data will be stored.
     *         
     * @catch FileNotFoundException
     */
    public void writeCustomerData(String fileName)
    {
        if (!customerMap.isEmpty())
        {
            PrintWriter pWriter = null;
            try {
                pWriter = new PrintWriter(fileName);
            }
            catch (FileNotFoundException ex) {
                System.err.println("\n\n*** File Not Found Exception ***");
                pWriter = null;
            }
            //for (Customer customer: customerList)
            for (Customer customer: customerMap.values())
            {
                customer.writeData(pWriter);
            }
            System.out.println("Customer list successfully saved!");
            pWriter.close();
        }
        else
        {
            System.out.println("Customers list is empty!");
        }
    }
    
    /**
     * Opens <code>fileName</code> a <code>FileDialog</code> object with an absolute file path.
     * The open file is passed to the <code>writeItemReservationData()</code> as parameter.
     */ 
    public void writeItemReservationData()
    {
        Frame myFrame = new Frame();
        FileDialog fileName = new FileDialog (myFrame,"Open", FileDialog.LOAD);
        fileName.setVisible(true);
        if (fileName !=null) {
            String filePath = fileName.getDirectory() + fileName.getFile();
        }
        writeItemReservationData(fileName.getFile());
    }
    
    /**
     * Writes item reservation data to a file
     * 
     * @parem  <code>fileName</code> a <code>String</code> the name of
     *         the text file in which the data will be stored.
     *         
     * @catch FileNotFoundException
     */
    public void writeItemReservationData(String fileName)
    {
        if (!itemReservationMap.isEmpty())
        {
            PrintWriter pWriter = null;
            try {
                pWriter = new PrintWriter(fileName);
            }
            catch (FileNotFoundException ex) {
                System.err.println("\n\n*** File Not Found Exception ***");
                pWriter = null;
            }
            for (ShopItemReservation itemReservation: itemReservationMap.values())
            {
                itemReservation.writeData(pWriter);
            }
            System.out.println("Reservation list successfully saved!");
            pWriter.close();
        }
        else
        {
            System.out.println("Reservation list is empty!");
        }
    }
    
    /**
     * Opens <code>fileName</code> a <code>FileDialog</code> object with an absolute file path.
     * The open file is passed to the <code>readItemData()</code> as parameter.
     */ 
    public void readItemData()
    {
        Frame myFrame = new Frame();
        FileDialog fileName = new FileDialog (myFrame,"Open", FileDialog.LOAD);
        fileName.setVisible(true);
        if (fileName !=null) {
            String filePath = fileName.getDirectory() + fileName.getFile();
        }
        readItemData(fileName.getFile());
    }
    
    /**
     * Reads data from a file and adds corresponding item values to itemMap.
     * The first scanner reads a line from a file and assigning it to a string. This string 
     * is then passed to the second scanner where the is passed to the ShopItem object and 
     * broken into tokens, assigned to the corresponding fields and added to the itemMap.
     * 
     * Uses try/catch instead of throwing the Exception
     *
     * @param   <code>filePath</code> a <code>String</code>, file object in which the loaded data is hold.
     * 
     */ 
    public void readItemData(String fileName)
    {
        Scanner scanner = null;
        File file = null;
        String typeOfData = "";
        try {
            file = new File(fileName);
            if (file == null)
            {
                System.out.println("You cancelled the choice");
            }    
            else
            {
                System.out.println("You chose " + file);
            }
            if (file.getName().toLowerCase().endsWith(".txt")) {
                System.out.println(file);
                try {
                    scanner = new Scanner(file);
                }
                catch (FileNotFoundException ex) {
                    System.err.println("\n\n*** File Not Found Exception ***");
                    System.err.println("Data file does NOT exist");
                }
                while (scanner.hasNextLine() )
                {
                    String lineOfText = scanner.nextLine().trim();
                    if (!lineOfText.startsWith("//")  && !lineOfText.isEmpty() )
                    {
                        if (lineOfText.startsWith("["))
                        {
                            typeOfData = lineOfText.toLowerCase();
                        }
                        else
                        {
                            Scanner scanner2 = new Scanner(lineOfText);
                            scanner2.useDelimiter("\\s*,\\s*");
                            ShopItem item = null;
                            switch (typeOfData) 
                            {
                                case "[electrictool data]":
                                item = new ElectricTool();
                                break;
                                case "[handtool data]":
                                item = new HandTool();
                                break;
                                case "[perishable data]":
                                item = new Perishable();
                                break;
                                case "[workwear data]":
                                item = new Workwear();
                                break;
                                default:
                                System.out.println("Unexpected data type!");
                            }
                            if (item != null && scanner2 !=null)
                            {
                                try {
                                    item.readData(scanner2);
                                    storeItem(item);
                                    scanner2.close();
                                    item = null;
                                }
                                catch (InputMismatchException ex) {
                                    System.err.println("\n\n*** Input MismatchException ***");
                                    System.err.println("Unexpected data field allocation!");
                                    file = null;
                                }
                            }
                        }    
                    }
                    else
                    {
                        scanner.hasNextLine();
                    }
                }
                scanner.close();
                printItemDetails();
            }
            else
            {
                System.out.println("Wrong file format!");
                System.out.println("Please select .txt file");
                file = null;
            }
        }
        catch (NullPointerException ex) {
            System.err.println("\n\n*** Null Pointer Exception ***");
            System.err.println("File not Found");
            file = null;
        }
    }

    /**
     * Opens <code>fileName</code> a <code>FileDialog</code> object with an absolute file path.
     * The open file is passed to the <code>readCustomerData()</code> as parameter.
     */ 
    public void readCustomerData()
    {
        Frame myFrame = new Frame();
        FileDialog fileName = new FileDialog (myFrame,"Open", FileDialog.LOAD);
        String filePath = new File("").getAbsolutePath();
        fileName.setDirectory(filePath);
        fileName.setVisible(true);
        if (fileName !=null) {
            readCustomerData(fileName.getFile());
        }
    }
    
    /**
     * Reads data from a file and adds corresponding item values to customerMap.
     * The first scanner reads a line from a file and assigning each it to a string. This string 
     * is then passed to the second scanner where the string is passed to the Customer object and 
     * broken into tokens, assigned to the corresponding fields and added to the customerMap.
     * 
     * Uses try/catch instead of throwing the Exception
     *
     * @param   <code>fileName</code> a <code>String</code>, file object in which the loaded data is hold.
     * 
     */ 
    public void readCustomerData(String fileName)
    {
        Scanner scanner = null;
        File file = null;
        Customer customer = null;
        String typeOfData = "";
        try {
            file = new File(fileName);
            if (file == null)
            {
                System.out.println("You cancelled the choice");
            }    
            else
            {
                System.out.println("You chose " + file);
            }
            if (file.getName().toLowerCase().endsWith(".txt")) {
                System.out.println(file);
                try {
                    scanner = new Scanner(file);
                }
                catch (FileNotFoundException ex) {
                    System.err.println("\n\n*** File Not Found Exception ***");
                    System.err.println("Data file does NOT exist");
                }
                while (scanner.hasNextLine() )
                {
                    String lineOfText = scanner.nextLine().trim();
                    if (!lineOfText.startsWith("//")  && !lineOfText.isEmpty() )
                    {
                            Scanner scanner2 = new Scanner(lineOfText);
                            scanner2.useDelimiter("\\s*,\\s*");
                            customer = new Customer();
                            if (customer != null & scanner2 != null)
                            {
                                try {
                                    customer.readData(scanner2);
                                    storeCustomer(customer);
                                    scanner2.close();
                                    customer = null;
                                }
                                catch (InputMismatchException ex) {
                                    System.err.println("\n\n*** Input MismatchException ***");
                                    System.err.println("Unexpected data field allocation!");
                                    customer = null;
                                }
                            }    
                    }
                    else
                    {
                        scanner.hasNextLine();
                    }
                }
                scanner.close();
                printCustomerDetails();
            }
            else
            {
                System.out.println("Wrong file format!");
                System.out.println("Please select .txt file");
                file = null;
            }
        }
        catch (NullPointerException ex) {
            System.err.println("\n\n*** Null Pointer Exception ***");
            System.err.println("File not Found");
            file = null;
        }
    }

    /**
     * Opens <code>fileName</code> a <code>FileDialog</code> object with an absolute file path
     * The open file is passed to the <code>readItemReservationData()</code> as parameter.
     */ 
    public void readItemReservationData()
    {
        Frame myFrame = new Frame();
        FileDialog fileName = new FileDialog (myFrame,"Open", FileDialog.LOAD);
        String filePath = new File("").getAbsolutePath();
        fileName.setDirectory(filePath);
        fileName.setVisible(true);
        if (fileName !=null) {
            readItemReservationData(fileName.getFile());
        }
    }
    
    /**
     * Reads data from a file and adds corresponding item values to itemReservationMap.
     * The first scanner reads a line from a file and assigning it to a string. This string 
     * is then passed to the second scanner where the is passed to the ShopItemReservation 
     * object and broken into tokens, assigned to the corresponding fields and added to the 
     * itemReservationMap.
     * 
     * Uses try/catch instead of throwing the Exception
     *
     * @param   <code>fileName</code> a <code>String</code>, file object in which the loaded data is hold.
     * 
     */ 
    public void readItemReservationData(String fileName)
    {
        Scanner scanner = null;
        File file = null;
        ShopItemReservation itemReservation = null;
        String typeOfData = "";
        try {
            file = new File(fileName);
            if (file == null)
            {
                System.out.println("You cancelled the choice");
            }    
            else
            {
                System.out.println("You chose " + file);
            }
            if (file.getName().toLowerCase().endsWith(".txt")) {
                System.out.println(file);
                try {
                    scanner = new Scanner(file);
                }
                catch (FileNotFoundException ex) {
                    System.err.println("\n\n*** File Not Found Exception ***");
                    System.err.println("Data file does NOT exist");
                }
                while (scanner.hasNextLine() )
                {
                    String lineOfText = scanner.nextLine().trim();
                    if (!lineOfText.startsWith("//")  && !lineOfText.isEmpty() )
                    {
                            Scanner scanner2 = new Scanner(lineOfText);
                            scanner2.useDelimiter("\\s*,\\s*");
                            itemReservation = new ShopItemReservation();
                            if (itemReservation != null & scanner2 != null)
                            {
                                try {
                                    itemReservation.readData(scanner2);
                                    storeItemReservation(itemReservation);
                                    scanner2.close();
                                    itemReservation = null;
                                }
                                catch (InputMismatchException ex) {
                                    System.err.println("\n\n*** Input MismatchException ***");
                                    System.err.println("Unexpected data field allocation!");
                                    itemReservation = null;
                                }
                            }    
                    }
                    else
                    {
                        scanner.hasNextLine();
                    }
                }
                scanner.close();
                printItemReservation();
            }
            else
            {
                System.out.println("Wrong file format!");
                System.out.println("Please select .txt file");
                file = null;
            }
        }
        catch (NullPointerException ex) {
            System.err.println("\n\n*** Null Pointer Exception ***");
            System.err.println("File not Found");
            file = null;
        }
    }
}
