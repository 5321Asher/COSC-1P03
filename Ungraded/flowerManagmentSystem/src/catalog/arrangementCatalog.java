package catalog;

import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIDisplayer;
import BasicIO.ASCIIOutputFile;
import arrangement.doArrg;
import arrangement.myArrangement;

public class arrangementCatalog implements catalog<myArrangement> {
    
    ASCIIDataFile in;
    ASCIIOutputFile out;
    ASCIIDisplayer list;
    
    cataArrgNode arrgHead, arrgTail;
    cataArrgNode current;
    
    String name;
    String type;
    String arrgFileName;
    
    public arrangementCatalog(String name, String type) {
        this.name = name;
        this.type = type;
        in = new ASCIIDataFile("arrangementCatalog.txt");
        out = new ASCIIOutputFile("arrangementCatalog.txt");
        arrgHead = null;
        current = null;
        arrgTail = null;
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
    public void setType(String newType) {
        this.type = newType;
    }
    
    @Override
    public void load() {
        myArrangement arrangement;
        
        name = in.readString();
        type = in.readString();
        
        while (!in.isEOF()) {
            
            arrangement = new myArrangement(in);
            
            arrgFileName = arrangement.getLoadFile();
            
            if (arrangement.getName() == null || arrangement.getName().isEmpty()) {
                continue;
            }
            addObject(arrangement);
        }
        in.close();
        if (arrgHead == null) {
            current = null;
        } else {
            current = arrgHead;
        }
    }
    
    @Override
    public void save() {
        out.writeString(name);
        out.writeString(type);
        out.writeLine("");
        
        cataArrgNode p = arrgHead;
        while (p != null) {
            out.writeString(p.c.getName());
            out.writeDouble(p.c.getPrice());
            out.writeString(p.c.getLoadFile());
            out.writeString(p.c.getPicFile());
            out.writeLine("");
            p = p.next;
        }
        out.close();
    }
    
    @Override
    public void listStart() {
        current = arrgHead;
    }
    
    @Override
    public myArrangement search(String search) {
        if (arrgHead == null) {
            return null;
        }
        
        cataArrgNode p = arrgHead;
        do {
            if (p.c.getName().toLowerCase().contains(search.toLowerCase())) {
                return p.c;
            }
            p = p.next;
        } while (p != null);
        
        return null;
    }
    
    @Override
    public void addObject(myArrangement object) {
        cataArrgNode newNode = new cataArrgNode(null, object, null);
        
        if (arrgHead == null) {
            arrgHead = newNode;
            arrgTail = newNode;
        } else {
            arrgTail.next = newNode;
            newNode.prev = arrgTail;
            arrgTail = newNode;
        }
        current = newNode;
        
    }
    
    @Override
    public void removeObject(String delete) {
        cataArrgNode p = arrgHead;
        
        while (p != null) {
            if (p.c.getName().equalsIgnoreCase(delete)) {
                if (p.prev != null) {
                    p.prev.next = p.next;
                } else {
                    // If removing the head
                    arrgHead = p.next;
                }
                
                if (p.next != null) {
                    p.next.prev = p.prev;
                } else {
                    // If removing the tail
                    arrgTail = p.prev;
                }
                current = (p.next != null) ? p.next : p.prev;
                return;
            }
            p = p.next;
        }
    }
    
    @Override
    public myArrangement getCurrent() {
        return current.c;
    }
    
    @Override
    public myArrangement up() {
        if (arrgHead == null) {
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
    public myArrangement down() {
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
    public myArrangement getFirstObject() {
        if (arrgHead == null) {
            return null;
        }
        current = arrgHead;
        return current.c;
    }
    
    @Override
    public void list() {
        cataArrgNode p = arrgHead;
        list = new ASCIIDisplayer();
        
        while (p != null) {
            list.writeLine(p.c.getName() + "    " + p.c.getPrice());
            p = p.next;
        }
        list.waitForUser();
        list.close();
    }
    
    public void createArrangement(String createName, double createPrice, String createPic) {
        myArrangement arrg = new myArrangement(createName, createPrice, createPic);
        addObject(arrg);
    }
    
    public void openItemList(myArrangement r) {
        doArrg arrg = new doArrg();
        
        ASCIIDataFile arrgIn = new ASCIIDataFile(r.getLoadFile());
        ASCIIOutputFile arrgOut = new ASCIIOutputFile(r.getLoadFile());
        
        arrg.open(arrgIn, r, arrgOut);
        arrgIn.close();
        arrgOut.close();
    }
}