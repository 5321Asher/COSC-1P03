package storage;
import java.util.Iterator;

public class Associator <E> implements Iterator<E> {
    private E[] entries;
    private int count, index;
    
    public Associator(E[] entries, int count) {
        this.entries = entries;
        this.count = count;
        this.index = 0;
    }
    
    @Override
    public boolean hasNext() {
        return index < count;
    }
    
    @Override
    public E next() {
        return entries[index++];
    }
}
