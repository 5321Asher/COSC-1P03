package arrangement;

import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIDisplayer;
import BasicIO.ASCIIOutputFile;
import item.myItem;

public class myArrangement {
    
    itemNode itemHead, itemTail;
    itemNode current;
    
    String name;
    double price;
    String loadFile;
    String picFile;
    
    public myArrangement(ASCIIDataFile from) {
        name = from.readString();
        if (from.successful()) {
            price = from.readDouble();
            loadFile = from.readString();
            picFile = from.readString();
        }
    }
    
    public myArrangement(String name, double price, String picFile) {
        this.name = name;
        this.price = price;
        loadFile = name + ".txt";
        this.picFile = picFile;
        ASCIIOutputFile out = new ASCIIOutputFile(loadFile);
        myItem filler = new myItem("replace", "replace this for you first item", 1, "filler", 1, "rose.png");
        addItem(filler, 1);
        saveArrangementItemList(out);
        itemHead = null;
        itemTail = null;
        current = null;
    }
    
    public String getLoadFile() {
        return loadFile;
    }
    
    public String getPicFile() {
        return picFile;
    }
    
    public void setPicFile(String newPicFile) {
        this.picFile = newPicFile;
    }
    
    public void saveArrangementItemList(ASCIIOutputFile out) {
        out.writeString(name);
        out.writeDouble(price);
        out.writeLine("");
        
        itemNode p = itemHead;
        while (p != null) {
            if (p.qty != 0) {
                out.writeString(p.c.getName());
                out.writeInt(p.qty);
                out.writeLine("");
            }
            p = p.next;
        }
    }
    
    public void listStart() {
        // Simply reset current to the head of the list
        current = itemHead;
    }
    
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
    
    public void setName(String newName) {
        name = newName;
    }
    
    public String getName() {
        return name;
    }
    
    public void setPrice(double newPrice) {
        price = newPrice;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void listItems() {
        itemNode p = itemHead;
        
        ASCIIDisplayer out = new ASCIIDisplayer();
        
        
        out.writeLine(name + "    $" + price);
        
        while (p != null) {
            if (p.qty != 0) {
                out.writeLine(p.c.getType() + "    " + p.c.getName() + "    " + p.qty + "    " + p.c.getInv() + "    " + p.c.getPrice());
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