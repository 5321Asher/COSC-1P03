package arrangement;

import item.myItem;

public class itemNode {
    int qty;
    myItem c;
    itemNode prev;
    itemNode next;
    
    public itemNode(itemNode p, myItem c , int qty, itemNode n) {
        prev = p;
        this.qty = qty;
        this.c = c;
        next = n;
    }
}
