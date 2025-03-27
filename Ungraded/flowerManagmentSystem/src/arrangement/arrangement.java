package arrangement;

import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIOutputFile;
import minor.*;

public interface arrangement {
    public void load(ASCIIDataFile in);
    
    public void save(ASCIIOutputFile out);
    
    public void listStart();
    
    public void search(String search);
    
    public void addItem(myItem item, int qty);
    
    public void removeItem(String search);
    
    public void changeItemQuantity(String search, int newQty);
    
    public void setName(String name);
    
    public String getName();
    
    public void setPrice(double price);
    
    public double getPrice();
    
    public void listItems();
    
}
