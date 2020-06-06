PROJECT TITLE: Tool Hire Shop
PURPOSE OF PROJECT: University programming project
IDE: BlueJ
DATE: April 2020
AUTHOR: Plamen Savchev 

**The project has been developed in 4 parts. Below you can find the instructions I have been given for each part.**

ASSIGNMENT INSTRUCTIONS:

**Part 1:**

It is very important that you start this project from scratch. DO NOT start with Homework 5 but write new code and that way you will learn a lot more. If you get stuck, you may look at previous code – but not immediately – to see how you got over that problem the first time. And start this project today.
Overview
The project is to develop an application which models a shop which hires out expensive tools for one-off usage. The model will contain classes which represent the shop, the tools hired out as well as the users of the Tool Hire shop and the staff who work for the shop. 

We shall start by introducing Tool and Shop classes, reading in tool data from text files and storing the tools in the hire system.


Step 1
Start a new project Tool Hire Part 1 Step 1 and create a Tool class with, initially, the following fields:

toolName e.g. DeWalt Circular Saw
itemCode e.g. RD6582 used to identify each tool
timesBorrowed
onLoan
cost which is the price of an item when new (in whole pounds)
weight in grammes

Provide a constructor for the class which has six parameters (in the order given above) for initialising the fields.  You should also provide accessor methods and a method printDetails() that outputs details of the tool to the terminal window e.g.

Tool name: DeWalt Circular Saw; code: RD6582; timesBorrowed: 250; 
onLoan: no; cost: 350; weight: 345

Now add a new class Shop to the project that, at this early stage of the project, has only one field: toolList used to store Tool objects in an array list.  Complete this first step by writing a method printAllTools() that displays the details of all tools in toolList. 

Finally, for this step, create a Test class as per previous homework sand continue to add to this as your project expands. 

Step 2
You are advised to read all of this step carefully before starting to code.

Firstly, save your project from Step 1 as Shop Part 1 Step 2 and continue to adopt this strategy for all subsequent steps so that you can always go back to a "working" version of your project.

The tool hire shop stores data corresponding to each of its tools in a text file called tool_data_1.txt. Download this file from BlackBoard and examine it.  Except for blank lines and comment lines, each line of the file contains comma separated data for the fields toolName, itemCode, timesBorrowed, onLoan, cost and weight e.g.

Makita BHP452RFWX,RD2001,12,false,14995,1800

You must now write a method readToolData() that reads this data, creates corresponding Tool objects and adds those objects to toolList. The name of the file that you read from, tool_data_1.txt, should be selected using a file dialog box -- the name should not be passed to readToolData() as a parameter. 

All that you need to know to set up and use a file dialog is either
•	in Java Supplementary Material 6: More on File Input and Output;
•	easily available in the documentation for the FileDialog class;
•	or is given below.  

Develop your readToolData()method in the following stages and again, it is recommended that you save a succession of projects, including one for each stage of Step 2. 

i)	Firstly, read Slides SM6.12-14. Then start writing your readToolData()method by creating a FileDialog object which will be used to select the file that will be used as input. Note that the constructor for the FileDialog object has as its first parameter a pointer to its parent or owner. However, for our purposes, you may set the value of this pointer to be null. Don’t worry as a FileDialog box will still appear though it may be behind your other windows !

To select the correct file for input, you may find it useful to use the method setDirectory().
Now output the name of the selected file to the terminal window to check that your code works.

ii)	Next, set up a Scanner object created by passing the constructor a File object based on the file selected in the file dialog but do not change the default delimiter.  Then use a loop that simply reads each line of the file, stores it in a String variable, say lineOfText, and prints out the string to the terminal window so that you can "see" all the lines of data that have been read.  At this stage:
•	do not attempt to break down each line into group, toolName, itemCode etc; 
•	let the readToolData() method throw any IOExceptions that may occur. 

The lines in the text file that start with // are comments. These, together with blank lines, should be ignored. Amend your code so that only the "real data" is output to the terminal window.

The only exception that can occur is actually a FileNotFoundException in the call to the constructor of the Scanner object so 
•	assuming you have one , remove the throws clause from the readToolData()method header; 
•	place the code that may cause that exception in a suitable try-catch block structure.

Before proceeding to Stage iii), make sure that the method behaves sensibly under all circumstances. 

iii)	Until now, we have adopted an approach that allowed us to easily ignore lines that were not "real data".  Now, for each line of real data, we need to extract the tokens from the string lineOfText.  But before you proceed any further, make sure that lineOfText does not have any leading or trailing spaces (note you are not allowed to edit the text file tool_data_1.txt !!).

Note the following points:
•	Although the "real data" in each line of the file tool_data_1.txt is essentially "comma separated" rather than "space separated", it may also contain spaces before or after a comma e.g.
Makita BHP452RFWX,RD2001,12 ,false,14995,1800
Bosch GSR10.8-Li Drill Driver,RD3021,25, true,9995,820
However, apart from the presence of "extra" spaces in the data, you may assume that all data files in the project contain valid data unless, at some later point, you are warned otherwise.

At the moment, your readToolData()method should have a loop that is similar to this
// set up scanner to read from file
while there is more data in the file
{
   read a line into the variable lineOfText
   if it's not a comment or a blank line
     print lineOfText to terminal window
}

Now, if you examine the documentation for the Scanner class, you will find that not only can a scanner take its input from a file, it can also take its input from a String.  When such a scanner is created, its constructor is passed the string.

It is this second Scanner object that you use to read the data into the fields of your classes. 

You should now declare and create a second Scanner object that is passed lineOfText as an argument.  This second scanner should use the Scanner class's useDelimiter() method which you met in Worksheet SM4.
You can now extract each token from lineOfText and so enable you to obtain values for each of the tool’s fields.  A corresponding Tool object can then be created and added to toolList.

After reading the data, check that all the tool data has been added to the list by calling the printAllTools()method.

Do not forget to amend your Test class to test your new code. 

Step 3
After you have completed Step 2, your pseudocode should be something like
// set up scanner to read from file
while there is more data in the file
{
   read a line into the variable lineOfText
   if it's not a comment or a blank line
     create a second scanner passing it lineOfText
     read data for each of tool's fields
     create a Tool object and store it in toolList
}

The approach we now have for reading in the tool data works well but does has the disadvantage that the Shop class is closely coupled with the Tool class (see Chapter 8, Designing classes).  For example, if an extra field is added to the Tool class then it will be necessary to change the code in the Shop class.  Also, as mentioned in the introduction, the "hire tool company" actually rents out specialist tools e.g. electric saws rather than "tools" and in Part 2 we will introduce subclasses of the Tool class to deal with this.  If we do not change the present approach, it will mean that the Shop class will then be closely coupled with both the Tool class and its subclasses.

So, we will next perform some refactoring of the code to improve it ready for later parts of the project.  We can uncouple the Shop and Tool classes by letting a Tool object read its own data from within the readToolData() method.  This can be accomplished by changing the if block in the above pseudocode to
   ...   
   if it's not a comment or a blank line
     create a second scanner passing it lineOfText
     create a Tool object
     pass the scanner to the readData() method of the Tool object
     store the Tool object in toolList
   ...
To code the above, you will also need to amend the Tool class by adding
•	a "no parameter" constructor. Without this, you cannot call the readData() method.
•	a method readData() that is passed a Scanner object which it then uses to read values for each of the tool’s fields.  

Testing for this step is exactly the same as for the previous step. 😊 


Step 4

The data file tools_data_2.txt contains more tool data and, if you examine the data, you will see that the data has been split into two types: electric tool data and handtool data. 

And, in addition to the previously seen data of toolName, toolCode, timesBorrowed, onLoan, cost and weight, the electric tool data now includes data for whether that tool is rechargeable and its power. The handtool data also has some additional different data to the electric tool data.

As such, the file contains lines such as
[Electric tool data]
that will not be relevant until later: modify the readToolData() method so that as well as ignoring comment lines and blank lines, it also (temporarily) ignores lines that start with [. 

Note that the "six parameter" form of the constructor is no longer needed and it can be deleted leaving only the "no parameter" form.  

With these changes you should now try to read in data from the file tool_data_2.txt and print out the fields that are common to both electric and hand tools but unfortunately, you will get an exception: a noSuchElement exception to be precise. Now debug your program so that you can print out just these fields. Hint: you need to add a single line of code to your program.


Demonstration details
At the appropriate workshop on 25th February, you will be expected to demonstrate this part of your project to one of the tutors.  
•	Make sure that you have the project available and that you can reproduce the work in the Newton laboratories.
•	You will be given an indication of the grade we will allocate to Part 1 of your project at the end of your demonstration.

Note that by the act of following these instructions and handing your work in, it is deemed that you have read and understand the rules on plagiarism as written in your student handbook.

**Part 2:**

Overview

Your code from Part 1 should successfully read in data from the file tool_data_2.txt which contained realistic tool data for electric and hand tools. However, only some of the data was able to be dealt with in the Tool class. In this part of the project, you will make it easier to deal with different types of tools by introducing subclasses of the Tool class and using inheritance to enhance the usability of your code. Finally, you will further develop the project by introducing a Customer class.

Step 1 Background and reading
In Part 1, we had only a Tool class but the data file tool_data_2.txt obviously contained data for handtools so the first step in this part of the project will be to introduce subclasses of the Tool class.  Before starting to code, read carefully this introduction.

The tool hire company offers a variety of tools to its customers and these tools are organised as shown in the diagram below :
 

ElectricTool and Handtool are direct subclasses of Tool and as well as the fields inherited from the Tool class:
•	the ElectricTool class has two additional fields: rechargeable and power;
•	the HandTool class has one additional field: sharpenable i.e. whether that tool is able to be sharpened. 


Reading Tip

Before starting to code, you may find it useful to have another look at the slides relating to the Network project of Chapter 10 and note that: 
•	The object post had static type Post but its dynamic type was either MessagePost or PhotoPost. 
•	The dynamic type of an object determined which display() method was executed.  For example, if the dynamic type was MessagePost then the display() method of the MessagePost class would be executed.  This is called method overriding. 
•	Although the display() method of the MessagePost class would be executed in the above example, the method calls its overridden superclass's display() method to output information about a post's fields before printing information about that message post's fields. 


The next steps are concerned with writing code that will read data for different types of tools.  We will firstly consider reading data for electric tools. It is obvious that we could write a method readElectricToolData() (that would be similar to a method readHandToolData()) and have a data file electricTool_data.txt. But, as our model grows, this approach means that we will end up with many "read" methods in the class Shop e.g. readElectricToolData(), readHandToolData(), readAnyOtherSimilarData() etc.  We will also end up with many different data files.  A more sensible approach is to have one "read" method readToolData() in the Shop class and all the data in one file. This is the approach that we will adopt here.

However, there is another issue. As an ElectricTool object has the data specific to electric tools and a HandTool object has the data specific to handtools, it makes sense for the readToolData() method to delegate the reading of the data for an ElectricTool object to the ElectricTool class and similarly for the data for a HandTool object to be read by the HandTool class. 

However, before we start coding, we should look at why in Part 1 of the project, we needed to use a "no parameter" constructor in Step 3 when we were trying to read the tool data. This was because we wanted to let each tool read its own data and to do that we needed to call tool.readData(scanner2). Clearly, in order to do this we must have created a Tool object before we call this method. However, our intention was to read the data from a file and TO THEN create an object to store this data.  BUT, we need to create this object in order to read the data: catch 22 !

And that's why we used a "no parameter" constructor. 

For each of the steps below, do not forget to update your Test class.

Step 2 Starting coding

We shall now add our first subclass: ElectricTool. Start coding by 
•	adding the subclass ElectricTool which should have the fields mentioned above;
•	then add a "no parameter" constructor that simply calls the constructor of its superclass but, remember, there is no need to initialise the fields as the default initialisations will be sufficient until actual data is read from a file;
•	then introduce a printDetails() method in the ElectricTool class that overrides the Tool class's printDetails() method, in a similar manner to that employed by the print() method of the Network project;
•	similarly, introduce a readData() method in the ElectricTool class that overrides the Tool class's readData() method.

At this point, your project should still compile and read the data corresponding to the fields of the Tool class.  However, until you modify the readToolData() method in the Shop class, it will not read the data corresponding to the extra fields of the ElectricTool class .  The reason for this should be obvious but if unsure ask a tutor.

Finally, for this step, a little bit of refactoring: because we are delegating the reading of data to the subclasses, we no longer need an inner while loop in readToolData() (if you did have one that is ), and this can safely be removed now. However, you might like to think whether the check (for your remaining while loop) that you have is the most appropriate. 

Step 3 Dealing with data specific to electric tools

You should have code in readToolData() corresponding to the pseudo-code below (or code that can be easily modified to this). Note that not all the details have been included in the pseudo-code.

while (there are more lines in the data file )

lineOfText is given the next line in the data file

if this line is not a comment and it is not blank 

// then it is either a flag or real data

then check whether the line is a flag and if so do nothing and if it is not a flag then deal with the line of data

It is important to note that this basic loop structure will not change -- in particular, you go round the loop once for each line of the data file. 
 

If you now look at the data file tool_data_2.txt, you will see that it is structured like this
[ElectricTool data]
data for an electric tool
data for another electric tool
...

[handtool data]
data for a handtool
data for another handtool
...
i.e. each block of data for a particular type of tool is preceded by a flag (such as [Electric tool data]) so that we know what kind of data to expect.  You can assume that any line that starts with a [, ignoring any leading spaces, is a flag denoting a type of data. 
So, each time you meet a flag, you need to record in your code what type of data comes next in the file. Use a local variable named typeOfData for this purpose. 
The lines following a flag are expected to be data which is to be read into your model.

Your code should now be altered to look like this new pseudocode:

while there are more lines in the data file 

lineOfText is given the next line in the data file

if this line is not a comment and it is not blank 

// then it is either a flag or real data

then check whether the line is a flag and, if so, set typeOfData to be equal to that flag and if it is not a flag then deal with the line of data appropriately

Other hints:
•	When your code corresponding to the statement labelled //A is executed: 
	the readData() method of the ElectricTool class should make sure that all the data for an ElectricTool object will be read in;
	the ElectricTool object assigned to the Tool variable (with its fields now properly initialised) will be stored in toolList;
	at this stage, the data for handtool objects can be ignored.

Check that your code works before proceeding : at this point you should be able to successfully read the electric tool data. If your code fails with an InputMismatchException error then that means you have read something that was not expected e.g. a Boolean instead of a String. In this case, add a println() statement to print out which data was read in and then debug accordingly.


Step 4 Dealing with handtool data

Next add the HandTool class (with the relationships shown in the diagram on Page 1) to the project and amend the readToolData() method so that it recognises this new data.  If you have successfully completed the previous steps with suitably structured code then this should be straightforward although, as usual, you will need to pay attention to detail. In fact, your Test class remains the same for this step as for the last few previous steps 😊.

Although, at this stage, you could simply deal with electric and hand tool data, better marks will be awarded for dealing with other possibilities i.e. for "unexpected flags".  If such a flag is encountered when reading from the file, print out a warning message though you will have to add a little more code than a single println(). 😊 
Step 5

In this step, you will start to extend the shop model to allow for more types of shop items. These additional items are accessories and are not available to be hired: these are items for sale only. Accessories can be items which are perishable e.g. liquids such as cleaning agents, or accessories may be workwear items e.g. gloves and other protective clothing.

Anyhow, what you need is a hierarchy of classes similar to this:
 

Firstly, think carefully before you start coding. Start by deciding which of the existing fields should be in ShopItem and which should be in Tool. 
Once you have decided which fields go into these two classes, start coding by adding a new ShopItem class to the project. This will require simple yet careful refactoring! 
Test your code by reading data from the file tool_data_2.txt and you will get an InputMismatchException. As you know, this means that the scanner has met a piece of data of a different type that it did not expect to meet. 
Try to work out why you get this exception (but do not try to rectify this problem) before reading the indented text below: 
Remember that in the Network example, in order to print the values of each field, the subclass’s print method was called which in turn called the print method in its superclass. 
•	Note that it could call the superclass method before or after printing its own fields or indeed, partway through printing its own fields. 
•	But, once the superclass method is called, all of the fields in the superclass will be printed. The point is that there is no mechanism for printing some of the superclass fields, then printing some of the subclass fields then printing the rest of the superclass fields. 
	Make sure this is clear: ask a tutor if unsure.
Now the way that values are distributed to the fields of a subclass and to the fields of a superclass behaves in the same way as printing these values. 
In other words, a subclass method receives all of the data for all of its fields (including the ones inherited from its superclass) and then sends some of that data to the superclass (to be stored in its fields) and then storing the remaining data in the fields of the subclass. 
You can think of the subclass read method receiving the data in one chunk - effectively the line of data – which is then split into separate pieces. Once – and once only – a “chunk” of that data is passed to the superclass. Because there is no to-ing and fro-ing between subclass and superclass in order to fill the superclass fields, this means that the “chunk” of data passed to the superclass must contain all of the data for the superclass fields.  
Why could this be a problem ? Well, the data that needs to be passed to ShopItem by Tool are values for itemName, itemCode and cost. And these three values are not collected together in a sequence in our data file. That is why you get an InputMismatchException. 
	Make sure this is clear: again ask a tutor if unsure.
Essentially, we need to re-order our data so that itemName, itemCode and cost are collected together on each line in the data file. This has been done for you in the file tool_data_3.txt which you should now use to test your latest code. The output of printAllDetails() should be the same as before you started this step, and, of course, formatted well.
Remember to avoid any duplication of code in the two classes.  You should also note that, since we are now dealing with items other than tools, the data that was labelled toolName should now be labelled itemName. 
Next, you should introduce the classes Accessory, Perishable and Workwear into the model. You will also need to extend the readData() method in the Shop class but if you have successfully completed earlier steps then this should be straightforward. 
You can test your code by using the file items_all.txt which contains data for ElectricTools and HandTools as well as for Perishables and for Workwear. 


Demonstration details
At the appropriate workshop on 10th March 2020, you will be expected to demonstrate this part of your project to one of the tutors.  
•	Make sure that you have the project available and that you can reproduce the work in the Newton laboratories.
•	You will be given an indication of the grade we will allocate to Part 2 of your project at the end of your demonstration.

•	If you miss this demonstration - without appropriate medical (or similar) evidence - you will be given zero for Part 2 of the project.


**Part 3:**

Overview				

Your code from Part 2 should successfully read in data from the files which contained realistic data for a variety of shop items into appropriate subclasses. The next phase is to further develop the project by introducing a Customer class and code to ensure that the customer ids, stored in a field customerID, can be relied on to be unique for each customer. 

Note: you should attempt all the steps in this assignment. However, if you do not complete Steps 3, 4 & 5, you can still complete Step 6. 

Limited help will be given on Steps 3, 4 & 5 as they are not needed to continue further with the project


Step 1 Introducing customers into the model
Anyone who wishes to hire a shop item must be registered as a customer of the company so we will now add a Customer class to the model.  The class should have String fields customerID, surname, firstName, otherInitials and title (e.g. Dr, Mr, Mrs, Ms) plus two constructors:
a)	One constructor  that always sets the customerID field to "unknown" though with parameters corresponding to the other four fields;
b)	A "no parameter" constructor which will be used in the readCustomerData() method later.
As well as accessor methods, the class should also have methods printDetails() and readData() similar in styleto previously written methods.

To make use of your Customer class, you will need to also modify the Shop class by adding:
•	A new field customerList which is initialised in the constructor;
•	A storeCustomer() method;
•	A printAllCustomers() method;
•	A readCustomerData() method to read in data from the data file customer_data.txt which can be downloaded from BlackBoard.  The method should be a cut-down version of Shop’s readData() method as this method does not need to check for lines starting with "[" as such lines are not present in the customer data files.

For your Test class, 
•	create a Customer object, using the form of the constructor above labelled a), with name "Mr John T Roberts" and add it to the list of customers;
•	display the customer list of this single customer;
•	read customer data from the file customer_data.txt;
o	this read will need user input (as it calls a dialogue box) and will suffice for this project. The other option is to have a second readData() method which takes a parameter.
•	display all the five customers.


Step 2 Saving your customer details

Now add a writeCustomerData() method to the Shop class.  This should use a PrintWriter object (introduced in Slides SM4) to write the data stored in customerList to a text file that is in a format similar to customer_data.txt.  The method should delegate the actual writing of the customer data to a writeData() method in the Customer class in the same way that we delegated reading shop item and customer data to the readData() methods of the ShopItem and Customer classes respectively.

When you have made these modifications, write code for your Test class to 
•	read customer data from the file customer_data.txt;
•	add a new customer;
•	write your customer list to a new file, called say new_customer_data.txt;
•	create a new Shop object;
•	read customer data from the file new_customer_data.txt;
•	display all the five customers.

Step 3 Allocating customer ID numbers

This step is concerned with allocating unique customerID numbers to Customer objects. 
To date, the customerID fields in the customer data files are all set to "unknown" indicating that these "new" customers have not yet been allocated an id.  In order to allocate ids to customers, we are going to write a method generateCustomerID() which has a String parameter corresponding to the prefix of the ID and an int corresponding to the number of digits in the ID.

So, if we call the method like this:
generateCustomerID("AB-", 6);
then it will generate ids such as AB-327280, AB-201114, AB-707381, AB-666566, etc. 
In addition, you will need to use a variable type Random (see Slides 6.15-6.17) that helps to generate random numbers.

It is recommended that you temporarily make this method public so that you can easily test that it works correctly from your Test class.  

Next add code to the storeCustomer() method so that if a Customer object with a customerID field set to "unknown" is being added to the customer list, a call is made to generateCustomerID() to provide a "proper" id. 

This step of the project is not needed for later steps of the project so it may skipped if you do not find a solution


Step 4 Generating unique customer ID numbers  

You may have noticed a problem with the generateCustomerID() method .  The purpose of a field such as customerID is to provide a unique way of identifying, in this case, a customer. But nothing has been done, so far, to ensure that the id values produced by generateCustomerID() are all different.  Think about how you would implement this and then implement it  
This step of the project is not needed for later steps of the project so it may skipped if you do not find a solution

Step 5 A remaining potential problem

Assuming that you have successfully amended your code to ensure that only customers with unique ids are stored, then there is one other issue concerning unique ids.  

One problem scenario would be, for example, that after you re-start the system, you firstly create another customer using the "four parameter" version of the Customer constructor and add it to the list.  How can you be sure that the id that has been allocated for the new customer has not already been allocated to one of the customers in the file customer_data.txt ?

Think through how best to solve this problem and then try to code it.


Step 6 Changing the way we store our data

The customers and shop items are currently stored in two ArrayList objects, customerList and itemsList.  In Slides 12, we saw how an ArrayList implements the List interface.  This means that customerList and itemsList objects can also be regarded as being of type List.  We saw a somewhat similar situation in Chapter 10 with the Network project where MessagePost and PhotoPost objects could also be regarded as being of type Post.

To check this, make appropriate changes to the declarations of the fields customerList and itemsList in the Shop class so that they are now of type List<Customer> and List<ShopItem> respectively.  Check that your code still compiles and executes correctly. Nothing has really changed, the two lists customerList and itemsList still reference array list objects 

An interface such as List allows a type of multiple inheritance, as discussed in Chapter 10, but it is important to remember that the add() method is not inherited by the classes ArrayList and LinkedList.  Instead, the fact that the classes implement the interface implies that they must each provide an add() method consistent with the one specified by List. Although the ArrayList and LinkedList classes all implement the List interface, they have different ways of storing the elements of a list and each has their own advantages and disadvantages.  

In our model, all instances of the Customer class have a field customerID that is intended to uniquely identify each customer.  Similarly, all ShopItem objects should have a field itemCode that uniquely identifies them.  If we are going to have these unique identifiers, it makes sense to re-think the way that we store customers and items.  Recall Slides 6.22-27 that gave examples of key/value pairs and briefly discussed mappings and the HashMap class.  This class is also an example of a class that implements an interface, in this case the Map interface.  

We can regard the Map interface as playing the same role for "mappings" as the List interface plays for "lists" and any class that implements it will be able to store key/value pairs.  A very significant feature of a mapping is that the value corresponding to a given key can be easily retrieved, unlike retrieving objects from a list where it is necessary to search through the list to find the object you want.  

One advantage of using Maps instead of Lists is that it will be a simple matter to easily retrieve a Customer object corresponding to a given customerID or a ShopItem object corresponding to a given itemCode. Hence, we will now make the change to the way that we store the customers and item and use a Map instead of a List.  

Start by performing a global edit in the Shop class and change the variable names customerList and itemsList, in your code, to customerMap and itemsMap respectively.  The name change is to more accurately reflect, in the code, how the customers and items are stored but users of the system can still think of having a "list of customers" and a "list of items".  

Next, change the declarations of customerMap and itemsMap from List to Map (re-read Slides 6.22-27, and think carefully about your choice of key value).  But, just as we needed, in this step, a particular type of List (e.g. ArrayList or LinkedList) in which to store the customers and items, we now need a particular type of Map.  We have seen a HashMap used in the Tech Support System project of Chapter 6 so we will choose to use a HashMap at this point.  If we decide later that it is not the best choice then, if you have coded sensibly, it is not difficult to change to another kind of Map (in a similar way to changing the kind of List earlier in this step).  

Replace the two calls to the constructor of the ArrayList class with appropriate calls to a constructor.  Unfortunately, even if you perform these changes correctly, it is not possible to check your work (at the moment) since you will find that your code will no longer compile.  

So you will need to go through your code making necessary modifications.  No major changes are needed, simply changes to "details" e.g. unlike a List, a Map does not have an add() method so you will need to replace statements such as  
itemsList.add(shopItem);
by some appropriate alternative.  However, you will probably find it useful know that the values() method of a Map object returns a "collection view" (e.g. a List) of the objects stored in a Map. 
When you can successfully compile the project, test that the code executes correctly.  You should be able to read in data, display shop items and customers, write out customer data in much the same way as you could at the end of Part 2 of the project.  

Next add methods getItem() and getCustomer() to the Shop class.  The methods are passed the id of a shop item or customer and return the corresponding item or customer from the appropriate map (or null if there is no corresponding item or customer).  

Demonstration details: 

At the appropriate workshop on Tuesday 21st April 2020, you will be expected to demonstrate this part of your project to one of the tutors.
•	Make sure that you have the project available and that you can reproduce the work in the Newton laboratories.
•	If you miss this demonstration - without appropriate medical (or similar) evidence - you will be given zero for Part 3 of the project.

**Part 4:**

Overview

Your code from Part 3 models a shop that now includes customers as well as different types of shop items (tools & accessories).  However, currently the model does not allow a customer to, for example, hire a particular tool.

So, in this part of the project, you will 
•	introduce a ShopItemReservation class that allows a customer to hire a shop item, i.e. to reserve that item, for a specified period.  To assist this, we will make use of
o	a DateUtil class that provides functionality for dealing with dates and the Date class of java.util; 
o	a Diary class that provides useful features for storing reservations.
•	add a GUI (Graphical User Interface) front end to your model.

Step 1

The purpose of this step is to explore the Date and DateUtil classes. The DateUtil class can now be downloaded from BlackBoard and added to your project. This class imports the Date class which is in the standard Java libraries.

An instance of the Date class "represents a specific instant in time, with millisecond precision" (see html documentation for the class) and can be used, together with classes such as Calendar, GregorianCalendar and SimpleDateFormat, to represent and manipulate dates and times.  In this project, we will concentrate on using it to represent simply "dates" (e.g. 23/03/2020) rather than "dates and times" (e.g. 23/03/2020 14:59). 

Open the project into which you downloaded the class DateUtil and read the introductory javadoc comments. Note the following:
•	The first two methods convertDateToLongString() and convertDateToShortString() convert Date objects to easily understandable strings e.g. "Monday, 23 March 2020" and "23-03-2020" where the "style" of the "long" and "short" versions of a date is determined by the fields longDatePattern and shortDatePattern. 
o	Do not change these patterns because Shop expects dates in these styles !  However it is clear that alternative "styles" are possible and the class could easily be enhanced by adding setlongDatePattern() and setshortDatePattern() methods.  
•	The convertStringToDate() method converts a string, in the style "23-03-2020", to a corresponding Date object.  We do this because such dates can easily be "manipulated" e.g. the following code 
Date today = DateUtil.convertStringToDate("23-03-2020");
Date todayWeek = DateUtil. incrementDate(today, 7);
System.out.println(DateUtil.convertDateToLongString(todayWeek));
will output "Monday, 30 March 2020" to the terminal window.

Give yourself confidence in using this class by looking at all the methods and reading their documentation. Once you have done this, (assuming you have added this class to your project), write code in your Test class that calls the method convertStringToDate() twice and then, using your two Date objects, calls the daysBetween() method and prints out the number of days between the two dates that you have chosen.

We shall now use these classes to allow customers to hire a shop item.

Step 2

A customer can already use the shop's displayDetails() method to see the shop items in the shop and, having seen them, that customer may then wish to reserve a particular item such as an expensive power tool or specialist item of workwear.  Such a reservation will need to specify the customer, the shop item, the start date for the reservation and the number of days the item is to be hired for.  It will also be convenient to give each reservation a unique id -- in this case we will store a six digit numerical string which will be generated sequentially e.g. 000001, 000002, 000003, 000004, etc

 
So, you should now introduce a ShopItemReservation class with:
•	three fields reservationNo, itemID, customerID, all of type String;
•	a field startDate of type Date: the date of the first day of the reservation;
•	a field noOfDays: the duration of the reservation.

Next write the code for the constructor for the ShopItemReservation class which should have five parameters reservationNo, itemID, customerID, startDate and noOfDays, in the order specified here.  The type of each parameter should be the same as the corresponding field except for the startDate parameter which should be of type String.  The argument passed for the startDate parameter should be in the shortDatePattern style mentioned above e.g. "23-03-2020".

Don't forget to test your code as you go along !

Any reservations that are made will need to be stored in the shop so next you need to modify the Shop class to handle the reservations by following these steps:
•	Introduce an itemReservationMap field to store the reservations.
o	Please note that we could call this field shopItemReservationMap but the names are getting too long and so I have “shortened” the method names from now on by dropping “shop” wherever possible. We will keep the “full” names for classes though.
•	Write a simple storeItemReservation() method similar to the storeItem() method (not the storeCustomer() method which is more complicated).  You should assume that the ShopItemReservation object passed to the method will have a unique reservationNo.
•	Write a method generateReservationNo() which generates the unique reservation number for a reservation. Remember to take account of the effect of closing your model on any future generated numbers. You should also "pad out" your numbers with zeros to produce, for example, 000001, 000002, 000003, 000004, etc.
o	If this step proves too difficult, simply generate any random number & move on to the next bullet point.
•	Write a getItemReservation() method.
•	Add a method with the following header
public void makeItemReservation(String customerID, String itemID, 
                            String startDate, int noOfDays)
Initially the method should simply  
o	get a reservation number for the reservation;
o	create a ShopItemReservation object;
o	add it to the list of reservations.
However, it is obvious that not all attempts to make a reservation will be valid -- for example, there may not be an item corresponding to the id passed to the method.  So make the method return a boolean, rather than void, and add checks at the beginning of the method to make sure that the four parameters are passed valid values:
o	if they are valid, the method should return true;
o	otherwise the method should output a message to the terminal window and return false without creating a ShopItemReservation object, etc.
There is also the possibility that a particular item may have already been reserved for all or part of the reservation period.  Do not attempt to check for this situation, we will return to this issue later .
•	Add methods printDetails() and printItemReservations() to the ShopItemReservation and Shop classes respectively.  These methods allow you to check that a "valid" reservation has been correctly added to the reservation list.
•	Finally for Step 2, add methods writeItemReservationData() and readItemReservationData() to the Shop class.  These methods should be similar to the writeCustomerData() and readCustomerData() methods and will need writeData() and readData() methods  in the ShopItemReservation class. You should be able to copy and paste much of the code  but note that you will need to use the DateUtil class to convert Date objects to and from String objects. 

Now that we have completed Step 2,
•	we are now able to save both customer and shop item reservation data so that, when the model is re-started, customer and shop item reservation data can be read in to restore the system to its previous state;
•	it would also be possible to treat the item data in a similar way and provide a writeItemData() but for the purposes of this project we will assume that the item data does not change and is always read in from the file items_all.txt.


Step 3		Note: you do not need complete this step before moving on to Step 4. 
If reservations were for one day only then it would be possible to easily check to see if an item selected by the customer was free on a particular day.  But reservations will typically span a number of days and that will make the checking more difficult e.g. the shop item that a customer might want to hire may be available on some days but not on others.  To overcome this problem and to provide other features, we have written a class Diary that can be downloaded from BlackBoard and added to the project.  A Diary object, as its name suggests, represents a diary which stores the reservations that have been made and the class provides the following public methods:

public void addItemReservation(ShopItemReservation reservation)
public void printEntries(Date startDate, Date endDate)
public void deleteItemReservation(ShopItemReservation reservation)
public ShopItemReservation[] getItemReservations(Date date)

Open the class and examine the Javadoc documentation which gives a brief description of each of these methods.  If you scroll down the code, you will find at line 120, that the class contains an inner class, called DayInDiary:
•	Just as a class can have fields, constructers and methods, it can also contain inner classes.
•	It would have been possible to make DayInDiary a public class in the usual way but sometimes there are advantages in having an inner class: 
o	in this case, the class is introduced only to help produce the functionality of the Diary class, it is never accessed (directly) by, for example, the Shop class;
o	making it an inner class makes it private (it is not possible to have an inner public class) and so improves encapsulation, see Chapter 8. 
•	The class DayInDiary itself contains yet another inner class, Entry !  However, note that the code could have been written slightly differently by making Entry an inner class of Diary rather than of DayInDiary.  But in its present form, it again improves encapsulation.
•	Although the inner classes are private and have fields, constructors and methods declared to be private, the private modifier does not affect accessibility in the usual way for inner classes.  Fields and methods are freely accessible between the outer and inner classes: e.g. an inner class can access a private field in Diary and vice-versa !

Note: you should not make any changes to the Diary class.

To make use of the class, add a field diary to Shop, initialise it in the constructor and add a call to the Diary class's addItemReservation() method in the storeItemReservation() method.  Whenever you now "make a reservation" using the makeItemReservation() method, the reservation will also be added to the "diary".  However, remember that we are not storing the reservation objects "twice" and using, potentially, a lot of memory: both the itemReservationMap and diary objects are merely storing the addresses of (or "pointers to") the same ShopItemReservation objects.

Now write a new method for the Shop class, printDiaryEntries() with String parameters, that calls the printEntries() method of the Diary class.  This will enable a user of the system to display diary entries for a specified period.  You might find it convenient to add a toString() method to the ShopItemReservation class that returns a string combining the reservationNo, customerId and itemID in an easily readable form.  Note that, for the purposes of this project, a reservation for a one day period (i.e. noOfDays is equal to 1) will have the same start and end date.

Your final tasks for this step of the project are
•	to amend the makeItemReservation() method so that it is not possible to make the reservation if the item is already reserved for all or part of the reservation period.  Hint: think how you would do this in real life and then look at the Diary class for any method that might help you. 
•	to write a method deleteItemReservation() that is passed a reservation number and deletes the corresponding reservation from the reservation system.

Step 4

In this step, we will perform some refactoring to make it easier to restart the reservation system and to restore it to its previous state

Firstly, rename your readData() method in Shop as readItemData(). 

In its current form, the readItemData() method always relies on the user to select the file for reading data, using a file dialog.  However, sometimes it will be convenient specify the name of the file by passing it as an argument when we call the method.  So reorganise the code into two methods, both called readItemData, where a non-parameter version:
•	uses a file dialog to get the name and path of the selected file;
•	creates the corresponding absolute path name;
•	calls a one-parameter version of readItemData(), passing it the absolute path name.
This should result in code similar to this:
public void readItemData()
{
   // use a file dialog then 
   if( fileName!=null )
      filePath = curDir + fileName;  // absolute path name

   readItemData(filePath);
}

public void readItemData(String filePath)
{
   //
   // remaining code here
   //
   File inFile = new File(filePath);

   // remainder of code from the original readItemData() method.
}
This should be straightforward  as it really only involves moving a few lines of code into a new readItemData() method and changing the File constructor call. 

Next, perform similar refactoring on the readCustomerData(), readItemReservationData(), writeCustomerData() and writeItemReservationData()methods ready for when we introduce other methods later.

Since, we said before that the shop item data will not change, we can now call the new version of the readItemData(), with the argument "items_all.txt", from the constructor of the Shop class so that the model is always populated with item data from the file each time the system is created. It also makes sense now – if you have not done this before – to re-name the printAllDetails() method as printItemDetails(). 

Next, allow the user to give your shop a name via a parameter to its constructor. Then add two new fields dumpCustomerDataFileName and dumpItemReservationDataFileName to the Shop class. Initialise these fields so  that their value is the name of the shop concatenated with either “customer dump.txt” or " shop item reservation dump.txt" where the word "dump" is used to indicate that this is where information will be downloaded to when the system closes (see below for more details).

Now write two new methods: 
•	closeDownSystem() that calls the writeCustomerData() and writeItemReservationData() methods so that the customer and item reservation data is written to the “dump” files pointed to by the variables dumpCustomerDataFileName and dumpItemReservationDataFileName respectively.
o	The intention is that this method will be called before closing down the reservation system.
o	Note that, provided you have written sensible code in your "write" methods, it will not matter if there is no customer or reservation data in the system -- the files will simply not be created. 
•	reloadSystem() that calls the readCustomerData() and readItemReservationData() methods so that customer and item reservation data is read from the two “dump” files 
o	It is possible that one or both of the dump files may not exist so each "read" method should only be called if the corresponding dump file exists. 
o	This method will be called when the shop is created and so will help restore the reservation system to the state it was in when it was last closed down. 

Finally, for this stage of refactoring, a very simple task .  Recall that in the Zoo class, Chapter 4, there was a method numberOfAnimals() that returned the number of animals stored in the collection of animals.  Write similar methods for the Shop class: getNumberOfItems(), getNumberOfCustomers() and getNumberOfReservations().

Before proceeding to the next step, make sure that
•	you can create a shop in which the system automatically reads in the data from "items_all.txt"; 
•	the model can successfully save customer and reservation data to your files without the user having to select file names when the shop is "closed down"; 
•	you can "restart" your model successfully and read customer and shop item data from your files to restore the shop to its previous state, again without the user having to select file names.


Step 5
In this step, we will add a GUI (Graphical User Interface) front end to your reservation system.  This will require you to
•	add a new class, downloaded from BlackBoard, to provide the GUI front end;
•	extend the GUI front end so that 
o	it can access all your reservation system's functionality;
o	it has extra functionality e.g. its Edit menu includes a Paste command as well as a Copy command.
We will now add the GUI front end to the reservation system that should
•	immediately allow you to access some of its functionality e.g. the ability to create a shop, load customer data, etc;
•	eventually allow you to access all its functionality e.g. make a reservation, display the diary, etc.  
Note that although the GUI front end will be a convenient way of accessing a reservation system, do not delete any code from your Test class.  It should be retained as it could help test any new functionality you add, in the future, to the Shop class.


Download, from BlackBoard, the file ShopWindowforBB.java and add the class to your project. Change its name to ShopWindow class and it should compile provided your Shop class has the methods mentioned in previous stages of the project with signatures and method names corresponding to the specifications given (or implied).

Now add code to your Test class that creates an object from this new class and runs this application. This will effectively render the Test class superfluous to requirements but do not delete it !

At the moment, the menu system allows you to create a "new" reservation system or to reload a system that has been created previously and to access some of the functionality in the Shop class.  Try using it by following these instructions:
•	Firstly, click on the Customer menu and take a quick look at the "commands".  Notice that the menu items are "greyed out" indicating that they are disabled -- this is because we have not yet opened a shop which would allow us to e.g. print its shop item details.
•	The Shop menu has menu items New shop..., Open shop..., Close shop and Exit:
o	the New Shop... "command" creates a new Shop object (using your Shop class) whose functionality can then be accessed using this GUI front end. 
o	the Open shop... "command" allows the user to reload a shop by specifying its name.  If a shop of the specified name does not already exist, it will be created. 
o	the Close shop "command", greyed out until either the New shop... or Open shop... is called, will close down a shop and ensure that its data is written to file by calling the closeDownShop() method;
o	the Exit "command", of course, closes the shop window.
•	Ignoring the Edit menu for a moment, a quick look at the Shop item, Customer and Reservation menus will show that all the menu items are disabled as no data is yet available (unless you have already used either the New shop or Open shop commands ). 
•	If you haven't already done so, create a new shop using New shop.  The shop window will change its title (it now includes the shop name that you typed in) and some functionality of the shop model will now be available.  For example:
o	The shop item data is automatically read in when the Shop constructor is called and so, for example, the previously disabled menu item Print all items on the Shop item menu is now enabled.  It can be used to display all the items in the Output View pane.
o	The Edit menu has menu items Copy, Clear and Print.  
	The first two allow a user to either copy selected text in the Output View pane to the clipboard or to clear the shop window.  These commands work -- try copying and clearing the text that was displayed when you used the New shop commands, etc.  The Copy command can also be called without using the menus -- the familiar Ctrl & C keystroke will copy any selected text to the clipboard.
	the Print "command" has not actually been implemented yet and again simply produces a message.
o	All other menu options are disabled for now 

Without trying to understand all the details, now take a look at the code in the ShopWindow class. You should be able to follow the sequence of calls from the constructor to initialise various menu items as well as what happens when you create a new shop. 

The class ShopWindow has been written in such a way that
1.	it does not depend, as far as is possible, on the other classes that you have written in the project i.e. it is loosely coupled.  Obviously, some coupling is essential if we wish to make use of the functionality (e.g. to print all shop item details) of the other classes.
2.	it is relatively easy to add menu items and generally modify its code.

Apart from making sure that your Shop class will work with the new ShopWindow class, there are four further tasks in this step:
i)	Your first task is to make the Load customers… command work. To help you, there is code implementing the Print all items... which makes it clear that you call the appropriate method in Shop. However, you must do something else before that menu option is made available. 

Then make the Print all customers... command work.  As there is already a similar "working" command Print all shop items..., it should be a very simple matter to add this functionality.

ii)	Your second task is to add functionality to display the details of a customer by specifying the customer's id. Hint: it will be helpful for you to write the sequence of steps needed to complete this task. 
Once you have added functionality to Print customer, code similarly for Print item. 

iii)	Your third task is to make the Make reservation... command work (assuming, of course, that the model has both shop item and customer data loaded so it is enabled). However, to check that your reservation has been correctly entered, you need to complete the next task below 
o	Hint: the static method parseInt() of the Integer class can be used to convert an "integer string" e.g. "23" to an int.
iv)	The final task for this step is to make the Print all reservations command work. Whilst, this does need some simple code adding to the actionPerformed() method, it also needs code elsewhere.


Hand in details: 


Your code will need to be submitted to BB by 16.00 on Monday May 11 at the latest. 

Note that by the act of following these instructions and handing your work in, it is deemed that you have read and understand the rules on plagiarism as written in your student handbook.


**NOTE: The following classes has been initially coded by my professor Dr.David Newton and edited by me: ShopWindow, Diary & DateUtil.**