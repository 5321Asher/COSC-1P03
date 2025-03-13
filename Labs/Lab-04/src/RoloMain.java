/**
 * @(#)RoloMain.java
 *
 *
 * @author 
 * @version 1.00 2010/2/24
 */

import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIDisplayer;
import BasicIO.BasicForm;
//import ROLODEX.*;

public class RoloMain {

	private BasicForm form;
	private BasicForm addForm;
	private ASCIIDisplayer msg;

    public RoloMain() {
     	BuildForm();
     }
     
    
    private void BuildForm(){
    
		form=new BasicForm("Up","Down","Add","Delete","List","Find","Exit");
		//form=new BasicForm()
		
		form.addTextField("first","First   ",30);
		form.addTextField("last","Last      ",30);
		form.addTextField("phone","Phone #",20);
		form.addTextField("address","Address",30);
		form.addTextField("find","Find    ",30);
		
		addForm=new BasicForm("Add","Cancel");
		//form=new BasicForm()
		
		addForm.addTextField("first","First   ",30);
		addForm.addTextField("last","Last      ",30);
		addForm.addTextField("phone","Phone #",20);
		addForm.addTextField("address","Address",30);

	//	form.addRadioButtons("sort","Sort By",false,rButtons);
	}//buildForm

 	private void loadContacts(Rolodex r) {
 		ASCIIDataFile in;
		Contact aContact;//Used for loading each contact in
		in=new ASCIIDataFile();

		while (true) {
			aContact=new Contact(in);
		if (in.isEOF()) break;
			System.out.println("Adding a Contact");
			r.Add(aContact);
		}
		in.close();
	}//loadContacts

   	private void displayContact(Contact c) {
		form.writeString("first",c.getFirst());
		form.writeString("last",c.getLast());
		form.writeString("phone",c.getPhoneNum());
		form.writeString("address",c.getAddress());
	}//displayContact 
    
    private void addNewContact(Rolodex r){
    	int button;
    	String first,last,phone,address;
    	Contact aContact;
    	
    	button = addForm.accept();
    	if (button == 1) return;
    		
		first=addForm.readString("first");
		last=addForm.readString("last");
		phone=addForm.readString("phone");
		address=addForm.readString("address");
		aContact=new Contact(first,last,phone,address);
		r.Add(aContact);    
		addForm.hide();	 	
    }
 
    private void doRolo(){
		int button;
		Contact current,tmp;//Current contact selected
		Rolodex myRolodex;
	    myRolodex = new RoloDlist();
	    msg = new ASCIIDisplayer();
		
		
		loadContacts(myRolodex);
		current = myRolodex.GetCurrent();
		while (true) {
			displayContact(current);
			button=form.accept();
		if (button==6) break;
		
			switch (button) {
				case 0: {
					current = myRolodex.Up();
					break;
				}
				case 1: {
					current=myRolodex.Down();
					break;
				}
				case 2:{
					addNewContact(myRolodex);
					current=myRolodex.GetCurrent();
					break;
				}	
				case 3: {
					myRolodex.Remove();
					current=myRolodex.Down();
					current=myRolodex.Up();
					break;	
				}							
				case 4: {
					myRolodex.List(msg);
					break;
				}
				case 5:{
					tmp = myRolodex.Find(form.readString("find"));
					if (tmp!=null)				
						current = tmp;
					break;	
				}				
				default: {
					System.out.println("Button Not Implemented");				
				}			
			}
		}
		form.close();
		//System.exit(0);
	}//doProcessing  	
    
    
   	public static void main ( String args[] ) {
		new RoloMain().doRolo();
		System.out.println("Lab4 Student");
	};
 
}