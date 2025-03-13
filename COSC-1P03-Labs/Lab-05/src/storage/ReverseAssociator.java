package storage;
import java.util.Iterator;

public class ReverseAssociator <E> implements Iterator<E> {
    private E[] entries;
    private int index;
    
    public ReverseAssociator(E[] entries, int count) {
        this.entries = entries;
        this.index = count - 1;
    }
    
    @Override
    public boolean hasNext() {
        return index >= 0;
    }
    
    @Override
    public E next() {
        return entries[index--];
    }
}
