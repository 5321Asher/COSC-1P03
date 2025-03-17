package Sort;

import java.util.Comparator;

/**
 * Comparator for sorting students by name.
 */
public class comapreName implements Comparator<Student> {
    public int compare(Student a, Student b) {
        return a.getName().compareTo(b.getName());
    }
}