package arrangement;

import minor.*;

public class arrangement {
    private int id;
    private String name;
    private String description;
    
    
    public arrangement(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    public void addFlower() {
        flowerList list = null;
        flower rose = new flower("rose", "a red flower", 20);
        flowerList newFlower = new flowerList(rose, 9);
        flowerList temp = list.next;
        
        if (temp == null) {
        list.next = newFlower;
        } else {
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newFlower;
        }
    }
    
    public void printFlowerList() {
        flowerList temp = list.next;
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.next;
        }
        
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}