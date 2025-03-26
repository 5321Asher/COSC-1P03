package arrangement;

import minor.myFlower;

public class flowerNode {
    int qty;
    myFlower c;
    flowerNode prev;
    flowerNode next;
    
    public flowerNode(flowerNode p, myFlower c , int qty, flowerNode n) {
        prev = p;
        this.qty = qty;
        this.c = c;
        next = n;
    }
}
