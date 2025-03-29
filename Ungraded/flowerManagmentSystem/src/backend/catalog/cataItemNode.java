package backend.catalog;

import backend.item.*;

public class cataItemNode {
    myItem c;
    cataItemNode next;
    cataItemNode prev;
    
    public cataItemNode(cataItemNode n, myItem c, cataItemNode p) {
        prev = p;
        this.c = c;
        next = n;
    }
}
