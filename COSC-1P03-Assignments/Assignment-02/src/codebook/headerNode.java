package codebook;

class headerNode {
    int count;  // Keeps track of list size
    node next;
    
    public headerNode() {
        this.count = 0;
        this.next = null;
    }
}