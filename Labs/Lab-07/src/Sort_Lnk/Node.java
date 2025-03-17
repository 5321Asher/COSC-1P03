package Sort_Lnk;


class Node {
    
    Node     next;
    Student  item;
    
    Node ( Student i , Node n ) {
        item = i;
        next = n;
    };  // constructor
    
}  // Node