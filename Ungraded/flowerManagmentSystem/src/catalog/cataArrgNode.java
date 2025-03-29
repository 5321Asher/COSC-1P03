package catalog;

import arrangement.myArrangement;

public class cataArrgNode {
    myArrangement c;
    cataArrgNode next;
    cataArrgNode prev;
    
    public cataArrgNode(cataArrgNode n, myArrangement c, cataArrgNode p) {
        prev = p;
        this.c = c;
        next = n;
    }
}
