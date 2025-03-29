package catalog;

import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIDisplayer;
import BasicIO.ASCIIOutputFile;
import item.*;

public class itemCatalog implements catalog <myItem>{
    
    ASCIIDataFile in;
    ASCIIOutputFile out;
    ASCIIDisplayer list;
    
    cataItemNode itemHead, itemTail;
    cataItemNode current;
    
    String type;
    String name;
    
    public itemCatalog(String name, String type) {
        this.name = name;
        this.type = type;
        in = new ASCIIDataFile("itemCatalog.txt");
        out = new ASCIIOutputFile("itemCatalog.txt");
        itemHead = null;
        itemTail = null;
        current = null;
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
    public String getType() {
        return type;
    }
    
    @Override
    public void setType(String newName) {
       type = newName;
    }
    
    @Override
    public void load() {
        myItem aItem;
        
        name = in.readString();
        type = in.readString();
        
        while (! in.isEOF()) {
            String itemType = in.readString();
            String itemName = in.readString();
            String itemDesc = in.readString();
            int inv = in.readInt();
            double price = in.readDouble();
            String file = in.readString();
            aItem = new myItem(itemName, itemDesc, inv, itemType, price, file);
            
            if (aItem.getName() == null || aItem.getName().isEmpty()) {
                continue;
            }
            addObject(aItem);
        }
        
        in.close();
        if (itemHead == null) {
            current = null;
        } else {
            current = itemHead;
        }
    }
    
    @Override
    public void save() {
        out.writeString(name);
        out.writeString(type);
        out.writeLine("");
        
        cataItemNode p = itemHead;
        while (p != null) {
            out.writeString(p.c.getType());
            out.writeString(p.c.getName());
            out.writeString(p.c.getDescription());
            out.writeInt(p.c.getInv());
            out.writeDouble(p.c.getPrice());
            out.writeString(p.c.getFile());
            out.writeLine("");
            p = p.next;
        }
        out.close();
    }
    
    @Override
    public void listStart() {
        current = itemHead;
    }
    
    @Override
    public myItem search(String search) {
        if (itemHead == null) {
            return null;
        }
        
        cataItemNode p = itemHead;
        do {
            if (p.c.getName().toLowerCase().contains(search.toLowerCase())) {
                return p.c;
            }
            p = p.next;
        } while (p != null);
        
        return null;
    }
    
    @Override
    public void addObject(myItem object) {
        cataItemNode newNode = new cataItemNode(null, object, null);
        
        if (itemHead == null) {
            itemHead = newNode;
            itemTail = newNode;
        } else {
            itemTail.next = newNode;
            newNode.prev = itemTail;
            itemTail = newNode;
        }
        current = newNode;
    }
    
    @Override
    public void removeObject(String delete) {
        cataItemNode p = itemHead;
        
        while (p != null) {
            if (p.c.getName().equalsIgnoreCase(delete)) {
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
                current = (p.next != null) ? p.next : p.prev;
                return;
            }
            p = p.next;
        }
    }
    
    @Override
    public void list() {
        cataItemNode p = itemHead;
        list = new ASCIIDisplayer();
        
        while (p != null) {
            list.writeLine(p.c.getType() + "    " + p.c.getName() + "     " + p.c.getInv() + "    " + p.c.getPrice());
            p = p.next;
        }
        list.waitForUser();
        list.close();
    }
    
    @Override
    public myItem getCurrent() {
        return current.c;
    }
    
    @Override
    public myItem up() {
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
    
    @Override
    public myItem down() {
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
    
    @Override
    public myItem getFirstObject() {
        if (itemHead == null) {
            return null;
        }
        current = itemHead;
        return current.c;
    }
}