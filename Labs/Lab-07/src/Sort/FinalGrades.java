package Sort;

import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIDisplayer;

import java.util.Comparator;


/**
 * This class is a program to produce a final grade report for students in a
 * course sorted by final grade.
 *
 * @author D. Hughes
 * @version 1.0 (Mar. 2014)
 * @see Student
 */

public class FinalGrades {
    
    
    private Student[] theClass = new Student[400];   // students in course
    private ASCIIDisplayer theReport;
    private int numStd;
    //Comparator <Student> comparator;
    
    /**
     * The constructor loads the students into the array.
     */
    
    public FinalGrades() {
        
        ASCIIDataFile gradeList;  // the grade list file
        
        gradeList = new ASCIIDataFile();
        loadStudents(gradeList);
        gradeList.close();
        theReport = new ASCIIDisplayer();
        theReport.writeString("Grade  St #       Name");
        theReport.newLine();
        sort(new comapreStdNum());
        sort(new comapreName());
        sort(new compareGrades());
        displayAll();
        
        theReport.close();
        
    }
    
    private void displayAll() {
        for (int i = 0; i < numStd; i++) {
            display(theClass[i]);
        }
    }
    
    ; // constructor
    
    
    /**
     * This method loads the student names & numbers from the classlist file and
     * creates the list of students in the course.
     */
    
    private void loadStudents(ASCIIDataFile classList) {
        numStd = 0;  // Initialize counter
        
        while (!classList.isEOF() && numStd < 400) {  // Added check to prevent going beyond array bounds
            try {
                theClass[numStd] = new Student(classList);
                numStd++;
            } catch (Exception e) {
                System.out.println("Error reading student: " + e.getMessage());
                break;
            }
        }
        
        classList.close();
    }
    
    ;  // loadStudents
    
    
    private void display(Student aStd) {
        
        theReport.writeString(aStd.getFinalGrade() + "  " + aStd.getStNum() + " " + aStd.getName());
        theReport.newLine();
        
    }
    
    ;  // display
    
    
    /**
     * This method sorts the class in descending order by final mark using
     * selection sort.
     */
    
   
    private void sort(Comparator<Student> comparator) {
        int largePos;  // position of largest item
        Student temp;
        
        for (int i = 0; i < numStd - 1; i++) {
            largePos = i;
            for (int j = i + 1; j < numStd; j++) {
                // Use the comparator to compare students
                // Using < 0 instead of <= 0 ensures stability
                if (comparator.compare(theClass[j], theClass[largePos]) < 0) {
                    largePos = j;
                }
            }
            temp = theClass[i];
            theClass[i] = theClass[largePos];
            theClass[largePos] = temp;
        }
    }  // sort
    
    
    /** This method sorts the class in descending order by final mark using
     * insertion sort.                                                          */
  
/*  private void sort ( ) {
 
        Student  temp;
        int      j;
        
        for ( int i=theClass.length-2 ; i>=0 ; i-- ) {
            temp = theClass[i];
            j = i + 1;
            while ( j < theClass.length
                    && theClass[j].getFinalGrade() > temp.getFinalGrade() ) {
                theClass[j-1] = theClass[j];
                j = j + 1;
            };
            theClass[j-1] = temp;
        };
        
    }; // sort */
    
    
    /** This method sorts the class in descending order by final mark using
     * exchange sort.
     */
/*  private void sort ( ) {
 
        Student  temp;
        
        for ( int i=theClass.length-1 ; i>=1 ; i-- ) {
            for ( int j=0 ; j<i ; j++ ) {
                if ( theClass[j+1].getFinalGrade() > theClass[j].getFinalGrade() ) {
                    temp = theClass[j];
                    theClass[j] = theClass[j+1];
                    theClass[j+1] = temp;
                };
            };
        };
    }; // sort */
    
    
    /**
     * This method sorts the class in descending order by final mark using merge
     * sort. It is actually a wrapper method for the recursive mergeSort method.
     */
  
/*    private void sort ( ) {

        mergeSort(theClass);
        
    }; // sort
    
    
    /** This method sorts the students in the class between the specified positions
      * into descending order by final mark using quicksort. It is usually used by
      * calling the wrapper method sort.                                         */
    private void mergeSort(Student[] toSort) {
        
        Student[] left;   // left partition
        Student[] right;  // right partition
        int lp;     // position in left on merge
        int rp;     // position in right on merge
        
        if (toSort.length > 1) {
            /* partition */
            left = new Student[toSort.length / 2];
            right = new Student[toSort.length - left.length];
            for (int i = 0; i < left.length; i++) {
                left[i] = toSort[i];
            }
            ;
            for (int i = 0; i < right.length; i++) {
                right[i] = toSort[left.length + i];
            }
            ;
            /* sort */
            mergeSort(left);
            mergeSort(right);
            /* merge */
            lp = 0;
            rp = 0;
            for (int i = 0; i < toSort.length; i++) {
                if (rp >= right.length) {
                    toSort[i] = left[lp];
                    lp = lp + 1;
                } else if (lp >= left.length) {
                    toSort[i] = right[rp];
                    rp = rp + 1;
                } else if (left[lp].getFinalGrade() >= right[rp].getFinalGrade()) {
                    toSort[i] = left[lp];
                    lp = lp + 1;
                } else {
                    toSort[i] = right[rp];
                    rp = rp + 1;
                }
                ;
            }
            ;
        }
        ;
        
    }
    
    ; // mergeSort */
    
    
    public static void main(String[] args) {
        FinalGrades f = new FinalGrades();
    }
    
    ;
    
    
} // FinalGrades