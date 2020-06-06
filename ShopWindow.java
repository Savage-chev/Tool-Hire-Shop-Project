// These import statements could be greatly reduced in number by using,
// for example, import java.awt.*;, but in this form we can clearly see
// all the classes imported
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
/**
 * Class ShopWindow is a front-end for the reservation system.
 * It concentrates the GUI aspects in one place and relies on the
 * Shop class to provide the shop model functionality.
 *
 * The window has an area for displaying output, for example the output
 * from System.out.println(), and a menu bar.
 *
 * @author D.E.Newton
 * @version Part 4 Step 5
 */
public class ShopWindow extends JFrame implements ActionListener
{
   private static final long serialVersionUID = 42L; // needed because JFrame is serializable, otherwise generates compiler warning
   private static int WIDTH = 800, HEIGHT = 600; // for the areaScrollPane, which will dictate the overall window size

   private Shop shop;
   private Container contentPane;
   private JTextArea outputArea;
   private HashSet<String> associatedTextSet; // for menus and menu items
   private JMenu shopMenu, editMenu, shopItemMenu, customerMenu, reservationMenu, helpMenu;

   /**
    * Constructor for objects of class ReservationSystemWindow
    */
   public ShopWindow()
   {
      associatedTextSet = new HashSet<String>();
      initialiseWindow();
   }

   private void initialiseWindow()
   {
      setTitle("Shop: None Loaded");
      setLocation(50, 50);
      setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

      contentPane = getContentPane();
      contentPane.setBackground(Color.magenta);  // magenta so it is obvious if can see the background when "layout" not worked correctly !
      contentPane.setLayout( new BoxLayout(contentPane, BoxLayout.Y_AXIS) );

      // this code, involving window adapter and window listener, is to ensure that if the
      // window is closed by using the "Close" icon at the top right hand corner of the
      // window then the "Exit" command is executed so that the system is closed down properly
      WindowAdapter windowListener = new WindowAdapter(){
                                             // override windowClosing()
                                             public void windowClosing(WindowEvent e)
                                             {
                                                ActionEvent action = new ActionEvent(this, 0, "Exit");
                                                actionPerformed(action);
                                             } };
      addWindowListener(windowListener);

      setupMenusAndActions();
      setUpOutputArea();

      setVisible(true);
   }

   private void setUpOutputArea()
   {
      // some basic ideas taken from
      //    http://www.jcreator.com/forums/index.php?showtopic=773
      //    http://javacook.darwinsys.com/new_recipes/14.9betterTextToTextArea.jsp
      outputArea = new JTextArea();
      outputArea.setFont(new Font("Courier", Font.PLAIN, 12));
      outputArea.setEditable(false);
      outputArea.setBackground(Color.white);
      outputArea.setLineWrap(true);
      outputArea.setWrapStyleWord(true);
      outputArea.setMargin(new Insets( 5, 10, 5, 10));

      JScrollPane areaScrollPane = new JScrollPane(outputArea);
      areaScrollPane.setPreferredSize( new Dimension(WIDTH, HEIGHT) );
      Border b = BorderFactory.createLoweredBevelBorder();
      areaScrollPane.setViewportBorder(BorderFactory.createTitledBorder(b, "Output View"));

      contentPane.add(areaScrollPane);
      pack();

      TextAreaOutputStream textOutput = new TextAreaOutputStream(outputArea);
      PrintStream capturedOutput = new PrintStream(textOutput);
      System.setOut(new PrintStream(capturedOutput));  // divert StandardOutput to capturedOutput
   }

   private Integer getKeyEventMnemonic(char letter)
   {

      if( !Character.isLetter(letter) )
      {
         // for non-letters, need to use KeyEvent.VK_XXX to associate letter
         System.out.println("\n*** Character <" + letter + "> is not a letter. ");
         System.out.println("*** Letters only are accepted for mnemonic association");
         System.exit(1); // none letter auto-detected, this can only occur during development so ok to halt
      }

      if( Character.isLowerCase(letter) )
      {
         System.out.println("\n*** Warning ");
         System.out.println("*** Mnemonic letter '" + letter + "' converted to upper case in getKeyEventMnemonic() ");
         System.out.println("*** When using the letter with the Alt key it is not case sensitive ");
         letter = Character.toUpperCase(letter);
      }

      // letters A-Z have simple associations
      int key = letter; // char assigned to int
      return key;
   }

   private JMenu setupMenu(JMenuBar menuBar, String menuText, char letter)
   {
      if( associatedTextSet.contains(menuText) )
      {
         // the text is used to identify the menu item and it should be unique to avoid confusion
         System.out.println("\n*** Aborting program");
         System.out.println("*** Attempt to define a menu with duplicate text \"" + menuText + "\" in setupMenu()");
         System.exit(2);  // duplicate auto-detected, this can only occur during development so ok to halt
      }
      int key = getKeyEventMnemonic(letter);
      JMenu menu =  new JMenu(menuText);
      menu.setMnemonic(key);
      menuBar.add(menu);
      return menu;
   }

   private void setupMenuItem(JMenu menu, String menuItemText, String menuItemTip,
                              char letter, boolean enabled)
   {
      setupMenuItem(menu, menuItemText, menuItemTip, letter, enabled, null);
   }

   private void setupMenuItem(JMenu menu, String menuItemText, String menuItemTip,
                              char letter, boolean enabled, KeyStroke keyStroke)
   {
      if( associatedTextSet.contains(menuItemText) )
      {
         // the text is used to identify the menu item and link it to an action so it must be unique
         System.out.println("\n*** Aborting program");
         System.out.println("*** Attempt to define a menu item with duplicate text \"" + menuItemText + "\" in setupMenuItem()");
         System.exit(3);  // duplicate auto-detected, this can only occur during development so ok to halt
      }
      associatedTextSet.add(menuItemText);

      int key = getKeyEventMnemonic(letter);
      JMenuItem menuItem = new JMenuItem(menuItemText);
      menuItem.addActionListener(this);
      menuItem.setEnabled(enabled);
      menuItem.setMnemonic(key);
      menuItem.setToolTipText(menuItemTip);
      if( keyStroke!=null )
      {
         menuItem.setAccelerator(keyStroke);
      }
      menu.add(menuItem);
   }

   private void setupMenusAndActions()
   {
      JMenuBar menuBar = new JMenuBar();

      // shop menu
      shopMenu = setupMenu(menuBar, "Shop", 'S');
      setupMenuItem(shopMenu, "New shop...", "Create a shop", 'N', true);
      setupMenuItem(shopMenu, "Open shop...", "Open a shop", 'O', true);
      setupMenuItem(shopMenu, "Close shop", "Close the shop", 'C', false);
      setupMenuItem(shopMenu, "Exit", "Close down and exit the model", 'X', true);

      // edit menu
      editMenu = setupMenu(menuBar, "Edit", 'E');
      setupMenuItem(editMenu, "Copy", "Copy selected text from Output area to clipboard", 'C', true, KeyStroke.getKeyStroke("ctrl C"));
      setupMenuItem(editMenu, "Clear", "Clear Output area", 'L', true);
      setupMenuItem(editMenu, "Print", "Print text in the output area", 'P', true);

      // shop item menu
      shopItemMenu = setupMenu(menuBar, "Shop item", 'I');
      setupMenuItem(shopItemMenu, "Print shop item...", "Display shop item with given code", 'V', false);
      setupMenuItem(shopItemMenu, "Print all shop items", "Display all shop items", 'D', false);
      setupMenuItem(shopItemMenu, "Delete shop item...", "Delete shop items with given code", 'F', false);
      setupMenuItem(shopItemMenu, "Delete all items", "Delete all shop items", 'T', false);

       // customer menu
      customerMenu = setupMenu(menuBar, "Customer", 'C');
      setupMenuItem(customerMenu, "Print customer...", "Display customer with given id", 'C', false);
      setupMenuItem(customerMenu, "Print all customers", "Display all customers in the shop", 'D', false);
      setupMenuItem(customerMenu, "Load customers...", "Read customer data from text file", 'L', false);
      setupMenuItem(customerMenu, "Delete customer...", "Delete customer with given code", 'F', false);
      setupMenuItem(customerMenu, "Delete all customers", "Delete all customers", 'T', false);

       // reservation menu
      reservationMenu = setupMenu(menuBar, "Reservation", 'R');
      setupMenuItem(reservationMenu, "Make reservation...", "Make a reservation of a shop item for a customer", 'M', false);
      setupMenuItem(reservationMenu, "Print all reservations", "Display all reservations in the model", 'D', false);
      setupMenuItem(reservationMenu, "Delete reservation...", "Delete reservation with given code", 'F', false);
      setupMenuItem(reservationMenu, "Delete all reservations", "Delete all reservations", 'T', false);
      
      // help menu
      helpMenu = setupMenu(menuBar, "Help", 'H');
      setupMenuItem(helpMenu, "Help contents", "Launch html documentation", 'C', true);
      setupMenuItem(helpMenu, "About", "About the shop", 'A', true);

      setJMenuBar(menuBar);
   }

   /**
    * method actionPerformed
    *
    *    Action events are generated by components such as buttons and menus in response
    *    to mouse clicks, keystrokes etc.  These events as passed to all registered
    *    EventListener objects to deal with as they see fit. ShopWindow objects
    *    will respond to action events generated by menu items because the menu items have
    *    registered "this" as a listener using the addActionListener() method.
    *
    * @param e, an ActionEvent
    */
   public void actionPerformed(ActionEvent e)
   {
      String action = e.getActionCommand();

      //
      // System menu
      //
      if( action.equals("New shop...") || action.equals("Open shop...") )
      {
         // these two actions grouped together to avoid duplicate code
         String prefix;
         if( action.equals("New shop...") )
         {
            prefix = "creating";
         }
         else
         {
            prefix = "re-loading";
         }

         String inputMessage = "Please input the name of the shop";
         String name = JOptionPane.showInputDialog(inputMessage);
         if( name!=null )
         {
            if( name.length()==0 )
               name = "Not named";
            System.out.println("\n " +  prefix + " shop \"" + name + "\"");
            shop = new Shop(name);
            if( action.equals("Open shop...") )
            {
            	shop.reloadSystem();
            }
            // add name of the shop to the window title
            String oldTitle = getTitle();
            int posnColon = oldTitle.indexOf(":");
            setTitle(oldTitle.substring(0, posnColon+2) + name);
            
            checkEnableStatusOfCommands();
         }
      }
      else if( action.equals("Close shop"))
      {
         shop.closeDownSystem(); // save data so can restart
         shop = null;
         outputArea.selectAll();
         outputArea.setText("");

         checkEnableStatusOfCommands();
      }
      else if( action.equals("Exit") )
      {
         if( shop!=null )
            // a shop has been created or opened so save data so can restart
            //shop.closeDownSystem();
         System.exit(0);  // close down the application
      }

      //
      // Edit menu
      //
      else if( action.equals("Copy") )
      {
         outputArea.copy();
      }
      else if( action.equals("Clear") )
      {
         outputArea.selectAll();
         outputArea.setText("");
      }
      else if( action.equals("Print") )
      {
         errorPrintln("\nAction \"" + action + "\" not fully implemented");
      }

      //
      // Shop item menu
      //
      else if( action.equals("Print shop item...") )
      {
         String inputMessage = "Please input Item ID";
         String input = JOptionPane.showInputDialog(inputMessage);
         if (input != null)
         {
             ShopItem item = shop.getItem(input);
             if (item != null)
             {
                 item.printDetails();
             }
         }
      }
      else if( action.equals("Print all shop items") )
      {
         shop.printItemDetails();
      }
      else if( action.equals("Delete shop item...") )
      {
         String inputMessage = "Please input Item ID";
         String input = JOptionPane.showInputDialog(inputMessage);
         if (input != null)
         {
             shop.removeItem(input);
         }
         checkEnableStatusOfCommands();
      }
      else if( action.equals("Delete all items") )
      {
         shop.clearItemMap();
         
         checkEnableStatusOfCommands();
      }
      //
      // customer menu
      //
      else if( action.equals("Print customer...") )
      {
         String inputMessage = "Please input Customer ID";
         String input = JOptionPane.showInputDialog(inputMessage);
         if (input != null)
         {
             Customer customer = shop.getCustomer(input);
             if (customer != null)
             {
                 customer.printDetails();
             }
         }
      }
      else if( action.equals("Print all customers") )
      {
         shop.printCustomerDetails();
      }
      else if( action.equals("Load customers...") )
      {
         shop.readCustomerData();
         checkEnableStatusOfCommands();
      }
      else if( action.equals("Delete customer...") )
      {
         String inputMessage = "Please input Customer ID";
         String input = JOptionPane.showInputDialog(inputMessage);
         if (input != null)
         {
             shop.removeCustomer(input);
         }
         checkEnableStatusOfCommands();
      }
      else if( action.equals("Delete all customers") )
      {
         shop.clearCustomerMap();
         
         checkEnableStatusOfCommands();
      }
      //
      // reservation menu
      //
      else if( action.equals("Make reservation...") )
      {
         String inputMessage = "Item ID";
         String input = JOptionPane.showInputDialog(inputMessage);
         String input1Message = "Customer ID";
         String input1 = JOptionPane.showInputDialog(input1Message);
         String input2Message = "Date of Hire";
         String input2 = JOptionPane.showInputDialog(input2Message);
         String input3Message = "Duration of hire(days)";
         String input3 = JOptionPane.showInputDialog(input3Message);
         if (input != null && input1 != null && input2 != null && input3 != null)
         {
             shop.makeItemReservation(input, input1, input2, Integer.parseInt(input3));
         }
         checkEnableStatusOfCommands();
      }
      
      else if( action.equals("Print all reservations") )
      {
         shop.printItemReservation();
      }
      else if( action.equals("Delete reservation...") )
      {
         String inputMessage = "Please input reservation No";
         String input = JOptionPane.showInputDialog(inputMessage);
         if (input != null)
         {
             shop.deleteItemReservation(input);
         }
         checkEnableStatusOfCommands();
      }
      else if( action.equals("Delete all reservations") )
      {
         shop.clearReservationMap();
         
         checkEnableStatusOfCommands();
      }
      //
      // Help menu
      //
      else if( action.equals("Help contents") )
      {
         String version = "If you have any enquiries about this product, please contact student @00533641 SYE543 Plamen Savchev of the University of Salford ;)."; 
         JOptionPane.showMessageDialog(this, version, "About the shop",
                                       JOptionPane.INFORMATION_MESSAGE);
      }
      else if( action.equals("About") )
      {
         String version = "Original Version: 4.11 (released 15 April, 2010)\n Edited Version: 4.5.4 (released 11 May, 2020)"; 
         JOptionPane.showMessageDialog(this, version, "About the shop",
                                       JOptionPane.INFORMATION_MESSAGE);
      }
      else
      {
         // not recognised
         errorPrintln("\n*** Warning");
         errorPrintln("*** Action \"" + e.getActionCommand() + "\" not recognised");
      }
   }

   private void checkEnableStatusOfCommands()
   {
      // called after every action that might affect the state of the shop

      if( shop!=null )
      {
         // disable New shop... and Open shop... and enable Close shop commands on Shop menu
         menuItemSetEnabled(false, shopMenu, "New shop...");
         menuItemSetEnabled(false, shopMenu, "Open shop...");
         menuItemSetEnabled(true, shopMenu, "Close shop");

         // enable Load commands
         menuItemSetEnabled(true, customerMenu, "Load customers...");

         boolean hasShopItems = shop.getNumberOfItems()>0;
         boolean hasCustomers = shop.getNumberOfCustomers()>0;
         boolean hasReservations = shop.getNumberOfReservations()>0;
         if( hasShopItems )
         {
            // enable other items on shop item menu
            menuItemSetEnabled(true, shopItemMenu, "Print shop item...");
            menuItemSetEnabled(true, shopItemMenu, "Print all shop items");
            menuItemSetEnabled(true, shopItemMenu, "Delete shop item...");
            menuItemSetEnabled(true, shopItemMenu, "Delete all items");
         }

         if( hasCustomers )
         {
            // enable other items on customer menu
            menuItemSetEnabled(true, customerMenu, "Print customer...");
            menuItemSetEnabled(true, customerMenu, "Print all customers");
            menuItemSetEnabled(true, customerMenu, "Delete customer...");
            menuItemSetEnabled(true, customerMenu, "Delete all customers");
         }

         if( hasShopItems && hasCustomers )
         {
            // enable Make reservation... item on shop menu
            menuItemSetEnabled(true, reservationMenu, "Make reservation...");
         }
         
         if ( hasReservations )
         {
             menuItemSetEnabled(true, reservationMenu, "Print all reservations");
             menuItemSetEnabled(true, reservationMenu, "Delete reservation...");
             menuItemSetEnabled(true, reservationMenu, "Delete all reservations");
         }
      }
      else
      {
         // no shop model

         // enable New shop... and Open shop... commands
         menuItemSetEnabled(true, shopMenu, "New shop...");
         menuItemSetEnabled(true, shopMenu, "Open shop...");

         // make sure all menu items in shop item, customer and reservation menus are disabled
         menuItemSetEnabled(false, shopItemMenu, "Print shop item...");
         menuItemSetEnabled(false, shopItemMenu, "Print all shop items");
         menuItemSetEnabled(false, shopItemMenu, "Delete shop item...");
         menuItemSetEnabled(false, shopItemMenu, "Delete all items");
         menuItemSetEnabled(false, customerMenu, "Load customers...");
         menuItemSetEnabled(false, customerMenu, "Print customer...");
         menuItemSetEnabled(false, customerMenu, "Print all customers");
         menuItemSetEnabled(false, customerMenu, "Delete customer...");
         menuItemSetEnabled(false, customerMenu, "Delete all customers");
         menuItemSetEnabled(false, reservationMenu, "Make reservation...");
         menuItemSetEnabled(false, reservationMenu, "Print all reservations");
         menuItemSetEnabled(false, reservationMenu, "Delete reservation...");
         menuItemSetEnabled(false, reservationMenu, "Delete all reservations");
      }
   }

   private void menuItemSetEnabled(boolean enable, JMenu menu, String actionCommand)
   {
      for(int i=0; i<menu.getItemCount(); i++)
      {
         JMenuItem item = menu.getItem(i) ;
         if( item.getActionCommand().equalsIgnoreCase(actionCommand) )
         {
            item.setEnabled(enable);
            return;
         }
      }

      // actionCommand not recognised
      errorPrintln("\n*** Warning -- Unexpected Error");
      errorPrintln("***\t Action \"" + actionCommand + "\" not recognised in method menuItemIsEnabled()");
      String enableAction;
      if( enable )
         enableAction = "enabled";
      else
         enableAction = "disabled";
      errorPrintln("***\t The action has NOT been " + enableAction);
      errorPrintln("***\t Please report this error to the System Administrator");
      errorPrintln("\n*** Note: The System Administrator is YOU if you have added ");
      errorPrintln("*** erroneous actionCommands.");
   }

   private void errorPrintln(String message)
   {
      // convenience method so can write error mesages to both System.err and System.out
      System.err.println(message);
      System.out.println(message);      
   }
   
   // inner class
   private class TextAreaOutputStream extends OutputStream
   {
      private final JTextArea textArea;
      private final StringBuilder sb = new StringBuilder();
      private boolean streamOpen = true;
      private boolean noError = true;

      public TextAreaOutputStream(JTextArea textArea)
      {
         this.textArea = textArea;
      }

      @Override
      public void flush()
      {
         // ignore
      }

      @Override
      public void close()
      {
         streamOpen = false;
      }

      @Override
      public void write(int b) throws IOException
      {
         // The print() and println() methods of the PrintStream class ultimately depend
         // on the abstract method write(int b) of the OutputStream class.  This subclass
         // overrides write(int b) and appends the character corresponding to b to a
         // StringBuffer object sb, unless that character corresponds to a line feed
         // character.  In the latter case, the contents of sb are appended to the JTextArea
         // and sb is cleared ready for the next call to write().
         if( !streamOpen )
         {
            if( noError )
            {
               // Only output a message first time write() invoked when stream closed.
               // Cannot (usefully) throw an IOException because the PrintStream that
               // uses TextAreaOutputStream merely sets its error state true and then
               // relies on checkError() being invoked.
               System.err.println("\n*** Unexpected error: TextAreaOutputStream closed when attempting to use write()");
               noError = false;
            }
            else
               return; // ignore call
         }

         if (b == '\r') // carriage return, do nothing
            return;

         if (b == '\n') // line feed, first copy string to textArea
         {
            textArea.append(sb.toString());
            sb.setLength(0);
         }

         sb.append((char)b);
      }
   }
}
