import java.util.Date;
/**
 * @author (@00533641 Plamen Savchev Group 1.1) 
 * @@version (4.5.1 10/05/20)
 * 
 * This class function is to test the methods from the Shop Tool Hire Prject
 * and all the classes within.
 */

public class Test
{
    private Shop shop;               //Reference to the Shop class
    
    private Customer customer;       //Reference to the Customer class
    
    private DateUtil dateUtil;       //Reference to the DateUtil class
    
    private ShopWindow shopWindow;   //Reference to the ShopWindow class
    
    /**
     * Constructor for objects of class Test
     */
    public Test()
    {
        //shop = new Shop();              //Creating new object of the Shop type
        shop = new Shop("P");            //Creating new object of the Shop type and passing name
        
        /**
         * STEP 3.1 CHECKING --> TESTING Create Customer Using Constructor with parameters; Display the customer list
         * of the single customer; Read customer data from file; Display all five customers.
         */
        //customer = new Customer("Roberts", "John", "T", "Mr");   //creating new customer
        
        //shop.storeCustomer(customer);               //adding the new customer to the array list
        
        //customer.printDetails();                    //printing the details of the single customer
        
        //shop.readCustomerData();                    //Loading, scanning & savilng the file into the array list
        
        //shop.clearCustomerList();                   //Clear all entries in the array list
        /**
         * STEP 3.2 CHECKING --> TESTING Read customer data from file; Add new customer; Write customer list to a file.
         */
        //shop.readCustomerData();  //("customer_data.txt");       //Loading, scanning & savilng the file into the array list
        
        //customer = new Customer("Roberts", "John", "T", "Mr");   //creating new customer
        
        //shop.storeCustomer(customer);                            //adding the new customer to the array list
        
        //shop.writeCustomerData("new_customer_data.txt");         //Write the data from the array onto txt file
         
        //shop.clearCustomerList();                                //Clear all entries in the array list
        
        //shop = new Shop();                                       //Creates new shop object
        
        //shop.readCustomerData();  //("new_customer_data.txt");   //Loading, scanning & savilng the file into the array list
        
        //shop.clearCustomerList();                                //Clear all entries in the array list
        /**
         * STEP 3.3 CHECKING --> TESTING CUSTOMER Testing customer data load with the new Id generator
         */
        //shop.readCustomerData();  //("customer_data.txt");       //Loading, scanning & savilng the file into the array list
        
        //shop.clearCustomerList();                                //Clear all entries in the array list
        /**
         * STEP 3.4 CHECKING --> TESTING CUSTOMER Testing customer data load with the new Id generator
         */
        //shop.readCustomerData();  //("customer_data.txt");          //Loading, scanning & savilng the file into the array list
        
        //shop.writeCustomerData("customer_data_withID.txt");         //Write the data from the array onto txt file
        
        //shop.readCustomerData();  //("customer_data_withID.txt");   //Loading, scanning & savilng the file into the array list
        
        //shop.clearCustomerList();                                   //Clear all entries in the array list
        /**
         * STEP 3.5 CHECKING --> TESTING CUSTOMER Testing customer data load with the new Id generator
         */
        //shop.readCustomerData();  //("customer_data.txt");       //Loading, scanning & savilng the file into the array list
        
        //customer = new Customer("Plamen", "Savchev", "B", "Mr"); //creating new customer
        
        //shop.storeCustomer(customer);                            //adding the new customer to the array list
        
        //shop.printAllCustomers();                                //Print all customer details
        
        //shop.clearCustomerList();                                //Clear all entries in the array list
        /**
         * STEP 3.6 CHECKING --> TESTING CUSTOMER Testing all methods after the changes been made to HashMap & new GET methods
         */
        //shop.readCustomerData("customer_data.txt");              //Loading, scanning & savilng the file into the hash map
        
        //customer = new Customer("Roberts", "John", "T", "Mr");   //creating new customer
        
        //shop.storeCustomer(customer);                            //adding the new customer to the hash map
        
        //shop.writeCustomerData("new_customer_data.txt");         //Write the data from the array onto txt file
         
        //shop.clearCustomerMap();                                 //Clear all entries in the hash map
        
        //shop.readCustomerData("new_customer_data.txt");          //Loading, scanning & savilng the file into the hash map
        
        //shop.clearCustomerMap();                                 //Clear all entries in the hash map
        
        //shop.readItemData("items_all.txt");                      //Loading, scanning & savilng the file into the hash map
        
        //shop.readItemData("tool_data_3.txt");                    //Loading, scanning & savilng the file into the hash map
        
        //shop.clearItemMap();                                     //Clear all entries in the hash map
        
        //shop.readCustomerData2("customer_data_withID.txt");      //Loading, scanning & savilng the file into the hash map
        
        //shop.getCustomer("AB-150338").printDetails();            //Get specific customer and prints it values

        //shop.removeCustomer("AB-150338");                        //Removing customer by its key 

        //shop.readItemData("items_all.txt");                      //Loading, scanning & savilng the file into the hash map
         
        //shop.getItem("PI1321").printDetails();                   //Get specific item and print it values
         
        //shop.removeItem("PI1321");                               //Removing customer by its key 
        /**
         * STEP 4.1 CHECKING --> TESTING the DateUtil class by creating objects for start and end date with convertToStringData()
         * and passing them to the daysBetween() method to calculate the difference
         */
        //int db = dateUtil.daysBetween(dateUtil.convertStringToDate("05-05-2020"),
        //dateUtil.convertStringToDate("28-05-2020"));                   //Converting & calculating days between dates
        
        //System.out.println(db);                                        //Print out the difference
        /**
         * STEP 4.2 CHECKING --> TESTING ShopItemReservation constructor, storeItemReservation(), generateReservationNo(),
         * getItemReservation(), makeItemReservation(), printItemReservation(), removeReservation(),
         * writeItemReservationData(), readItemReservationData()
         */
        //shop.readItemData("items_all.txt");                            //Loading, scanning & savilng the file into the hash map
        
        //shop.readCustomerData("customer_data_withID.txt");             //Loading, scanning & savilng the file into the hash map
        
        //shop.makeItemReservation("AB-150338", "RD2001", "07-05-20", 7); //Creating a new reservation
        // ===> This method uses generateReservationNo() & storeItemReservation() which uses getItemReservation()
        
        //shop.makeItemReservation("AB-879305", "RD2834", "08-05-20", 7); //Making second reservation to check the generator
        
        //shop.printItemReservation();                                    //Print all reservation details
        
        //shop.writeItemReservationData("item_reservation_data.txt");     //Write the data from the map onto txt file
        
        //shop.clearReservationMap();                                     //Clears the reservation map
        
        //shop.readItemReservationData();                                 //Read reservations from a txt file
        
        //shop.removeReservation("00001");                                //Removes reservation by reservation number
        /**
         * STEP 4.4 CHECKING --> TESTING makeItemResrvation() call to addReservation(), second makeItemReservation() to check if item 
         * already reserved, printDiaryEntries() & deleteItemReservation() from itemReservationMap and diary.
         */
        //shop.readItemData("items_all.txt");                            //Loading, scanning & savilng the file into the hash map
        
        //shop.readCustomerData("customer_data_withID.txt");             //Loading, scanning & savilng the file into the hash map
        
        //shop.makeItemReservation("RD2001", "AB-150338", "07-05-20", 7); //Creating a new reservation
        
        //shop.makeItemReservation("RD2001", "AB-879305", "08-05-20", 7); //Making second reservation with same itemID
        
        //shop.printDiaryEntries("07-05-20", "20-05-20");                 //Printing entries from the diary between given dates
        
        //shop.deleteItemReservation("000000");                           //Removes reservation from itemReservation map & diary
        /**
         * STEP 4.3 CHECKING --> TESTING reloadSystem() --> check for NON existant files, closeDownSystem() --> check if 
         * customer/reservations exists, adds data to customerMap and itemReservationMap, closeDownSystem() when maps filled
         * to write onto new files, clears customerMap and itemReservationMap, reloadSystem() to test the files created by the
         * closeDownSystem() method. All read methods have been using as a call in one or more of the moethod below.
         */
        //shop.reloadSystem();                                              //Check to load when non existant files
        
        //shop.closeDownSystem();                                           //Check to close when no customers/reservation
        
        //shop.readCustomerData("customer_data_withID.txt");                //Loading, scanning & savilng the file into the hash map
        
        //shop.makeItemReservation("RD2001", "AB-150338", "07-05-20", 7);   //Creating a new reservation
        
        //shop.closeDownSystem();                                           //Check to close when no customers/reservation
        
        //shop.clearCustomerMap();                                          //Clear all entries in the hash map
        
        //shop.clearReservationMap();                                       //Clears the reservation map
        
        //shop.reloadSystem();                                              //Check to load when non existant files
        /**
         * STEP 4.5 CHECKING --> 
         */
        shopWindow = new ShopWindow();
    }
}
