package item;

import BasicIO.ASCIIDataFile;

public class myItem implements item {
    
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
    
    @Override
    public void setFile(String newFile) {
        this.file = newFile;
    }
    
    @Override
    public String getFile() {
        return file;
    }
    
    @Override
    public double getPrice() {
        return price;
    }
    
    @Override
    public void setPrice(double newPrice) {
        price = newPrice;
    }
    
    @Override
    public String getType() {
        return type;
    }
    
    @Override
    public void setType(String newType) {
        type = newType;
    }
    
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void setName(String newName) {
        name = newName;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
    
    @Override
    public void setDescription(String newDescription) {
        description = newDescription;
    }
    
    @Override
    public int getInv() {
        return inv;
    }
    
    @Override
    public void setInv(int newInv) {
        inv = newInv;
    }
}