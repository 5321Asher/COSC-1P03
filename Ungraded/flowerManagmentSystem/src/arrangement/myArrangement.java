package arrangement;

import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIDisplayer;
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
    public myItem search(String name) {
        if (itemHead == null) {
            return null;
        }
        
        itemNode p = itemHead;
        do {
            if (p.c.getName().toLowerCase().contains(name.toLowerCase())) {
                return p.c;
            }
            p = p.next;
        } while (p != null);
        
        return null;
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
        
        ASCIIDisplayer out = new ASCIIDisplayer();
        
        
        out.writeLine(name + "    $" + price);
        
        while (p != null) {
            if (p.qty != 0) {
                out.writeLine(p.c.getType() + "    " + p.c.getName() + "    " + p.qty + "    " + p.c.getInv());
            }
            p = p.next;
        }
        out.waitForUser();
        out.hide();
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
    
    public int displayItemQty() {
        if (current == null) {
            return 0;
        }
        return current.qty;
    }
    
    public myItem getFirstItem() {
        if (itemHead == null) {
            return null;
        }
        current = itemHead;
        return current.c;
    }
}