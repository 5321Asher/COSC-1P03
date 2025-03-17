package Sort;

import java.util.Comparator;

/**
 * Comparator for sorting students by final grade (descending order).
 */
public class compareGrades implements Comparator<Student> {
    public int compare(Student a, Student b) {
        // For descending order, we return negative when a > b
        if (a.getFinalGrade() > b.getFinalGrade()) {
            return -1;
        } else if (a.getFinalGrade() < b.getFinalGrade()) {
            return 1;
        } else {
            return 0;
        }
    }
}