package Sort;

import java.util.Comparator;

/**
 * Comparator for sorting students by student number.
 */
public class comapreStdNum implements Comparator<Student> {
    public int compare(Student a, Student b) {
        return a.getStNum().compareTo(b.getStNum());
    }
}