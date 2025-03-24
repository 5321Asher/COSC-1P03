package arrangement;

import minor.vase;

public class vaseList {
    vase vaseType;
    int vaseQuantity;
    vaseList next;
    
    public vaseList(int quantity,vase vaseType) {
        this.vaseQuantity = quantity;
        this.vaseType = vaseType;
        this.next = null;
    }
}
