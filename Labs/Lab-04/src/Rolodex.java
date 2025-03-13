/**
 * @(#)Rolodex.java
 *
 *
 * @author 
 * @version 1.00 2010/2/24
 */

import BasicIO.ASCIIDisplayer;

public interface Rolodex {

   public void Add(Contact c);   	//Adds contact to the structure
   public void Remove();	//Removes contact from the structure
   public Contact Up();			 	//Returns the next contact in the structure
   public Contact Down();        	//Returns the previous contact in the structure
    
   public Contact GetCurrent();     //Returns the current contact from the data structure
    
    	
   public void List(ASCIIDisplayer out);  	//Lists the entire rolodex to the ascii
   									  		// displayer out 
    
   public Contact Find(String name);  //Finds the contact name in the structure and returns


    
}