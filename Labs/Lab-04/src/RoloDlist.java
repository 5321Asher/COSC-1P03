


/**
 * @(#)RoloDlist.java
 * @author
 * @version 1.00 2010/2/24
 */

import BasicIO.ASCIIDisplayer;

public class RoloDlist implements Rolodex {
    
    protected node theList, tail;
    protected node current;
    
    
    public RoloDlist() {
        Contact aContact;
        node ptr;
        aContact = new Contact("dummy", "zzzzzzzzzzzzzzzzz", "dummy", "dummy");
        theList = new node(null, aContact, null);
        aContact = new Contact("dummy", "A", "dummy", "dummy");
        ptr = new node(null, aContact, theList);
        theList.prev = ptr;
        theList = ptr;
        tail = theList.next;
        current = null;
    }
    
    public void Add(Contact c) {//Adds contact to the structure
        node ptr, qtr;
        ptr = theList;
        
        //System.out.println(c.last + " " + ptr.c.getLast());
        
        while (ptr.next != null) {
            ptr = ptr.next;
        }
        //System.out.println(c.last + " " + ptr.c.getLast());
        qtr = new node(ptr.prev, c, ptr);
        qtr.prev.next = qtr;
        ptr.prev = qtr;
        current = qtr;
    }
    
    
    public void Remove() {    //Removes contact from the structure
        if (current == null || theList == null || theList.next == null) {
            return;
        }
        
        if (current == theList && current.next == tail) {
            theList = null;
            tail = null;
            current = null;
            return;
        }
        
        node prevNode = current.prev;
        node nextNode = current.next;
        
        if (prevNode != null) {
            prevNode.next = nextNode;
        } else {
            theList = nextNode;
        }
        
        if (nextNode != null) {
            nextNode.prev = prevNode;
        } else {
            tail = prevNode;
        }
        
        if (nextNode != null)
            current = nextNode;
        else
            current = prevNode;
        
    }
    
    public Contact Up() {//Returns the next contact in the structure
        
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
    
    public Contact Down() {//Returns the previous contact in the structure
        
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
    
    public void List(ASCIIDisplayer out) {  //Lists the entire rolodex to the ascii
        node p = theList.next;
        while (p.next != null) {
            
            out.writeString(p.c.getFirst().toLowerCase());
            out.writeString(p.c.getLast().toLowerCase());
            out.writeString(p.c.getAddress());
            out.writeString(p.c.getPhoneNum());
            out.writeLine(" ");
            p = p.next;
        }
        
    }
    
    
    public Contact GetCurrent() {
        return current.c;
    }
    
    
    public Contact Find(String name) { //find a char string which matches the last name
        node p = theList;
        while (p != null) {
            p = p.next;                                                             
            if ((p.c.getLast().toLowerCase().contains(name.toLowerCase()))) {
                return p.c;
            }
            
        }
        
        
        
        return null;
        //dummy code Remove when inplementing
    }  //Finds the contact name in the structure and returns
    //that contact.
}