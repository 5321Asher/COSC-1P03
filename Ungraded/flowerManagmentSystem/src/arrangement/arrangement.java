package arrangement;

import minor.*;

public interface arrangement {
    public void addFlower(myFlower flower, int qty);
    
    public void removeFlower(String search);
    
    public void addVase(myVase vase, int qty);
    
    public void removeVase(String search);
    
    public void changeFlowerQuantity(String search, int newQty);
    
    public void changeVaseQuantity(String search, int newQty);
    
    public void setName(String name);
    
    public String getName();
    
    public void setPrice(double price);
    
    public double getPrice();
    
    public void listItems();
    
}
