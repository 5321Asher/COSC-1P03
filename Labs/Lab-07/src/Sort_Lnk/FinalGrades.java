package Sort_Lnk;

import BasicIO.*;


/** This class is a program to produce a final grade report for students in a
  * course sorted by final grade.
  * 
  * @see Student
  *
  * @author D. Hughes
  *
  * @version 1.0 (Mar. 2014)                                                     */

public class FinalGrades {
    
    
    private Node            theClass;   // students in course
    private int             numStd;     // number of students
    private ASCIIDisplayer  theReport;  // the report
    
    /** The constructor loads the students into the array.                       */
    
    public FinalGrades ( ) {
        
        ASCIIDataFile   gradeList;  // the grade list file
        Node            p;          // travelling pointer
                
        gradeList = new ASCIIDataFile();
        loadStudents(gradeList);
        gradeList.close();
        theReport = new ASCIIDisplayer();
        theReport.writeString("Grade  St #       Name");
        theReport.newLine();
        theReport.writeString("before"); theReport.newLine();
        p = theClass.next;
        while ( p != null ) {
            display(p.item);
            p = p.next;
        };
        sort();
        theReport.writeString("after"); theReport.newLine();
        p = theClass.next;
        while ( p != null ) {
            display(p.item);
            p = p.next;
        };
        theReport.close();
        
    }; // constructor
    
    
    /** This method loads the student names & numbers from the classlist file and
      * creates the list of students in the course.                              */
    
    private void loadStudents ( ASCIIDataFile classList ) {
        
        Node  p;
        
        numStd = classList.readInt();
        theClass = new Node(null,null);
        p = theClass;
        for ( int i=0 ; i<numStd ; i++ ) {
            p.next = new Node(new Student(classList),null);
            p = p.next;
        };
        classList.close();
        
    };  // loadStudents
    
    
    private void display ( Student aStd ) {
        
        theReport.writeString(aStd.getFinalGrade()+"  "+aStd.getStNum()
                                  +" "+aStd.getName());
        theReport.newLine();
        
    };  // display
    
    
    /** This method sorts the class in descending order by final mark using
      * exchange sort.                                                           */
    
    private void sort ( ) {
        
        Node  p;
        Node  q;
        
        for ( int i=1 ; i<numStd ; i++ ) {
            q = theClass;
            p = theClass.next;
            while ( p.next != null ) {
                if ( p.next.item.getFinalGrade() > p.item.getFinalGrade() ) {
                    q.next = p.next;
                    p.next = p.next.next;
                    q.next.next = p;
                };
                q = q.next;
                p = q.next;
            };
        };
        
    }; // sort
    
    
    public static void main ( String[] args )
                                            { FinalGrades f = new FinalGrades(); };
    
    
} // FinalGrades