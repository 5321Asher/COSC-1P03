package arrangement;

import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIOutputFile;
import BasicIO.BasicForm;
import minor.myItem;

public class myArrangement implements arrangement {
    
    itemNode itemHead, itemTail;
    itemNode current;
    
    ASCIIOutputFile out;
    
    BasicForm form;
    
    String name;
    double price;
    
    public myArrangement(String name, double price) {
        this.name = name;
        this.price = price;
        // Initialize list to empty
        itemHead = null;
        itemTail = null;
        current = null;
    }
    
    @Override
    public void load(ASCIIDataFile in) {
        // Read the arrangement name and price
        name = in.readString();
        price = in.readDouble();
        
        // Clear any existing items
        itemHead = null;
        itemTail = null;
        current = null;
        
        // Load items
        while (!in.isEOF()) {
            String type = in.readString();
            String itemName = in.readString();
            String itemDesc = in.readString();
            
            int itemQty = in.readInt();
            int itemInv = in.readInt();
            
            myItem aItem = new myItem(itemName, itemDesc, itemInv, type);
            addItem(aItem, itemQty);
        }
    }
    
    @Override
    public void save(ASCIIOutputFile out) {
        out.writeString(name);
        out.writeDouble(price);
        out.writeLine("");
        
        itemNode p = itemHead;
        while (p != null) {
            if (p.qty != 0) {
                out.writeString(p.c.getType());
                out.writeString(p.c.getName());
                out.writeString(p.c.getDescription());
                out.writeInt(p.qty);
                out.writeInt(p.c.getInv());
                out.writeLine("");
            }
            p = p.next;
        }
    }
    
    @Override
    public void listStart() {
        // Simply reset current to the head of the list
        current = itemHead;
    }
    
    @Override
    public void search(String search) {
        itemNode p = itemHead;
        
        while (p != null) {
            if (p.c.getName().equals(search)) {
                System.out.println(p.c.getName());
                System.out.println(p.c.getDescription());
                System.out.println(p.c.getType());
                System.out.println("qty: " + p.qty);
                System.out.println("inv: " + p.c.getInv());
                break;
            }
            p = p.next;
        }
    }
    
    @Override
    public void addItem(myItem c, int qty) {
        // Create a new node
        itemNode newNode = new itemNode(null, c, qty, null);
        
        // If list is empty
        if (itemHead == null) {
            itemHead = newNode;
            itemTail = newNode;
        } else {
            // Add to the end of the list
            itemTail.next = newNode;
            newNode.prev = itemTail;
            itemTail = newNode;
        }
        
        // Set current to the newly added item
        current = newNode;
    }
    
    @Override
    public void removeItem(String search) {
        itemNode p = itemHead;
        
        // Find the item to remove
        while (p != null) {
            if (p.c.getName().equalsIgnoreCase(search)) {
                // Adjust links to remove this node
                if (p.prev != null) {
                    p.prev.next = p.next;
                } else {
                    // If removing the head
                    itemHead = p.next;
                }
                
                if (p.next != null) {
                    p.next.prev = p.prev;
                } else {
                    // If removing the tail
                    itemTail = p.prev;
                }
                
                // Update current
                current = (p.next != null) ? p.next : p.prev;
                return;
            }
            p = p.next;
        }
    }
    
    @Override
    public void changeItemQuantity(String search, int newQty) {
        itemNode p = itemHead;
        
        while (p != null) {
            if (p.c.getName().equalsIgnoreCase(search)) {
                p.qty = newQty;
                return;
            }
            p = p.next;
        }
    }
    
    @Override
    public void setName(String newName) {
        name = newName;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void setPrice(double newPrice) {
        price = newPrice;
    }
    
    @Override
    public double getPrice() {
        return price;
    }
    
    @Override
    public void listItems() {
        itemNode p = itemHead;
        
        while (p != null) {
            if (p.qty != 0) {
                System.out.println(p.c.getType() + "    " + p.c.getName() + "    " + p.qty + "    " + p.c.getInv());
            }
            p = p.next;
        }
    }
    
    public myItem getCurrent() {
        return current.c;
    }
    
    public myItem up() {//Returns the next contact in the structure
        
        if (current == null) {
            return null;
        }
        if (current.prev == null) {
            while (current.next != null) {
                current = current.next;
            }
        } else {
            current = current.prev;
        }
        return current.c;
        
    }
    
    public myItem down() {//Returns the previous contact in the structure
        
        if (current == null) {
            return null;
        }
        if (current.next == null) {
            while (current.prev != null) {
                current = current.prev;
            }
        } else {
            current = current.next;
        }
        return current.c;
    }
    
    public int getItemQty(String search, ASCIIDataFile in) {
        /*
        String temp = in.readString();
        int numTemp = (int) in.readDouble();
        
        while (! in.isEOF()) {
            temp = in.readString();
            String searchName = in.readString();
            temp = in.readString();
            if (searchName.equalsIgnoreCase(search)) {
                int itemQty = in.readInt();
                return itemQty;
            }
            numTemp = in.readInt();
        }*/
        return 0;
    }
    
    public int displayItemQty() {
        return current.qty;
    }
}