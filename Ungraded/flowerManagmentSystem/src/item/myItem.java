package item;

import BasicIO.ASCIIDataFile;

public class myItem {
    
    String name;
    String description;
    int inv;
    String type;
    double price;
    String file;
    
    public myItem(ASCIIDataFile from) {
        type = from.readString();
        if (from.successful()) {
            name = from.readString();
            description = from.readString();
            int skip = from.readInt();
            inv = from.readInt();
            price = from.readDouble();
            file = from.readString();
        }
    }//constructor
    
    public myItem(String name, String description, int inv, String type, double price, String file) {
        this.name = name;
        this.description = description;
        this.inv = inv;
        this.type = type;
        this.price = price;
        this.file = file;
    }
    
    public void setFile(String newFile) {
        this.file = newFile;
    }
    
    public String getFile() {
        return file;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double newPrice) {
        price = newPrice;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String newType) {
        type = newType;
    }
    
    
    public String getName() {
        return name;
    }
    
    public void setName(String newName) {
        name = newName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String newDescription) {
        description = newDescription;
    }
    
    public int getInv() {
        return inv;
    }
    
    public void setInv(int newInv) {
        inv = newInv;
    }
}