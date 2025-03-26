package minor;

public class myVase implements vase {
    
    String name;
    String description;
    int inv;
    String type;
    
    public myVase (String name, String description, int inv, String type) {
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