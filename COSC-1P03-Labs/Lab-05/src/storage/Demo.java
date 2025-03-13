package storage;

import java.util.Iterator;

public class Demo {
    public Demo() {
        Associative<String> arr = new AssociativeArray<>(10);
        arr.add("1", "apple");
        arr.add("2", "banana");
        arr.add("3", "orange");
        arr.add("4", "lemon");
        arr.add("5", "dragonfruit");
        
        System.out.println("== Normal Order ==");
        for (String s : arr) {
            System.out.println(">" + s);
        }
        System.out.println("\n== Reverse Order ==");
        Iterator<String> revit = ((AssociativeArray<String>) arr).reverseIterator();
        while (revit.hasNext()) {
            System.out.println(">" + revit.next());
        }
    }
}
