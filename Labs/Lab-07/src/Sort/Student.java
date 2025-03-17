package Sort;

import BasicIO.ASCIIDataFile;


/**
 * This class represents a student in a course. A student has a student number,
 * a name and a final mark.
 *
 * @author D. Hughes
 * @version 1.0 (Mar. 2014)
 * @see
 */

public class Student implements Comparable<Student> {
    
    
    private String stNum;       // student number
    private String name;        // student's name
    private double finalGrade;  // final mark
    
    
    /**
     * This constructor creates a new student reading the student number, name
     * and final grade from a specified file. If there are no more students in the
     * file, it returns and from.isEOF() will be true.
     *
     * @param from the file to read from
     */
    
    
    public Student(ASCIIDataFile from) {
        
        stNum = from.readString();
        if (! from.isEOF()) {
            name = from.readString();
            finalGrade = from.readDouble();
        }
        ;
        
    }
    
    ; // constructor
    
    @Override
    public int compareTo(Student other) {
        return name.compareTo(other.getName());
    }
    
    
    /**
     * This method returns the student number of the student.
     *
     * @return String  the student number
     */
    
    public String getStNum() {
        
        return stNum;
        
    }
    
    ; // getStNum
    
    
    /**
     * This method returns the student's name.
     *
     * @return String  the student's name
     */
    
    public String getName() {
        
        return name;
        
    }
    
    ; // getName
    
    
    /**
     * This method returns the final grade for the student.
     *
     * @return double  the student's final grade
     */
    
    public double getFinalGrade() {
        
        return finalGrade;
        
    }
    
    ; // getFinalGrade
    
    
} // Student