package storage;
import java.util.Iterator;

    public interface Associative<E> extends Iterable<E> {
        void add(String key, E entry);
        E get(String key);
        void delete(String key);
        int count();
        String[] keys();
        Iterator<E> iterator();
    }

