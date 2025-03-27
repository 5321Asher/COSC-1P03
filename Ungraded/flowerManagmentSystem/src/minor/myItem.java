package minor;

import BasicIO.ASCIIDataFile;

public class myItem implements item {
    
    String name;
    String description;
    int inv;
    String type;
    
    public myItem(ASCIIDataFile from) {
        type = from.readString();
        if (from.successful()) {
            name = from.readString();
            description = from.readString();
            int skip = from.readInt();
            inv = from.readInt();
        }
    }//constructor
    
    public myItem(String name, String description, int inv, String type) {
        this.name = name;
        this.description = description;
        this.inv = inv;
        this.type = type;
    }
    
    public String getType() {
        return type;
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