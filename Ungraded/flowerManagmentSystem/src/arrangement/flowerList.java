package arrangement;

import minor.flower;

public class flowerList {
    flower flowerType;
    int flowerQuantity;
    flowerList next;
    
    public flowerList(flower flowerType, int quantity) {
        this.flowerQuantity = quantity;
        this.flowerType = flowerType;
        this.next = null;
    }
}
