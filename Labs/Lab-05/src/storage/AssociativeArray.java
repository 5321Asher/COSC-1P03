package storage;

import java.util.Iterator;

public class AssociativeArray<E> implements Associative<E> {
    private String[] keys;
    private E[] arr;
    private int count;
    
    public AssociativeArray(int capacity) {
        count = 0;
        keys = new String[capacity];
        arr = (E[]) new Object[capacity];
    }
    
    public int count() {
        return count;
    }
    
    public E get(String key) {
        //System.out.println("Searching for key : " + key);
        
        for (int i = 0; i < count; i++) {
            if (keys[i].equalsIgnoreCase(key)) {
                return arr[i];
            }
        }
        throw new RuntimeException("UnderflowException : Key Not Found");
    }
    
    @Override
    public void add(String key, E entry) {
        int index = 0;
        while (index < count && ! keys[index].equals(key)) {
            index++;
        }
        if (index == keys.length) {
            throw new RuntimeException("Overflox exception : No Space Left");
        }
        
        //System.out.println("adding key :" + key + " and entry : " + entry);
        
        keys[index] = key;
        arr[index] = entry;
        if (index == count) {
            count++;
        }
    }
    
    @Override
    public void delete(String key) {
        int index = 0;
        while (index < count && ! keys[index].equals(key)) {
            index++;
        }
        if (index < count) {
            for (int i = index; i < count - 1; i++) {
                keys[i] = keys[i + 1];
                arr[i] = arr[i + 1];
            }
            count--;
            keys[count] = null;
            arr[count] = null;
        }
    }
    
    @Override
    public String[] keys() {
        String[] k = new String[count];
        for (int i = 0; i < count; i++) {
            k[i] = keys[i];
        }
        return k;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new Associator<>(arr, count);
    }
    
    public Iterator<E> reverseIterator() {
        return new ReverseAssociator<>(arr, count);
    }
    
    public static void main(String[] args) {
        new Demo();
    }//new push
}