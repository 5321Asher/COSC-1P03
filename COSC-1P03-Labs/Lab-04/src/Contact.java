import BasicIO.ASCIIDataFile;

/** Contact.java
 ** A single 'contact' in the rolodex.			*/

public class Contact {
	String first;
	String last;
	String phoneNum;
	String address;
	
	
	public Contact (){};               //default constructor
	
	
	public Contact(ASCIIDataFile from) {
		first=from.readString();
		if (from.successful()) {
			last = from.readString();
			phoneNum=from.readString();
			address=from.readString();
		}
	}//constructor
	
	public Contact(String first,String last, String phoneNum, String address) {
		this.first=first;
		this.last=last;
		this.phoneNum=phoneNum;
		this.address=address;
	}//constructor
	
	public String getFirst() {
		return first;
	}//getName
	
	public String getLast(){
		return last;
	}	
	
	public String getPhoneNum() {
		return phoneNum;
	}//getPhoneNum
	
	public String getAddress() {
		return address;
	}//getAddress
}