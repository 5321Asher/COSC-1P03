package arrangement;

import minor.myVase;

public class vaseNode {
    myVase c;
    int qty;
    vaseNode prev;
    vaseNode next;
    
    public vaseNode(vaseNode p, myVase c, int qty, vaseNode n) {
        prev = p;
        this.qty = qty;
        this.c = c;
        next = n;
        
    }
}
