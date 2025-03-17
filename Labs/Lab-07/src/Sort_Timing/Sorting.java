package Sort_Timing;


import BasicIO.*;


/** This class serves as a testbed for the insertion selection, exchange and
  * merge sort sorting algorithms. It performs a timing test of a sort of a table
  * of 2,500 random entries.
  *
  * @author D. Hughes
  *
  * @version 1.0 (Mar. 2014)                                                     */

public class Sorting {
    
    
    private static final int SIZE = 100000;  // size of table
    
    private ASCIIDisplayer  out;     // display for results
    private int[]           table;   // the table to be sorted
    private int[]           oTable;  // the original (unsorted) table
    
    
    /** The constructor tests the sorting algorithms.    */
    
    public Sorting ( ) {
        
        long t1;
        long t2;
        
        out = new ASCIIDisplayer();
        
        out.newLine();
        out.writeString("Sorting a ");
        out.writeInt(SIZE);
        out.writeString(" entry table");
        out.newLine();
        out.newLine();
        makeTable();
        
        table = (int []) oTable.clone(); // this makes an exact copy of the table
        out.writeString("Using selection sort: ");
        t1 = System.currentTimeMillis();
        selSort();
        t2 = System.currentTimeMillis();
        out.writeLong(t2-t1);
        out.writeString(" milliseconds");
        out.newLine();
        
        table = (int []) oTable.clone(); // this makes an exact copy of the table
        out.writeString("Using insertion sort: ");
        t1 = System.currentTimeMillis();
        insSort();
        t2 = System.currentTimeMillis();
        out.writeLong(t2-t1);
        out.writeString(" milliseconds");
        out.newLine();
        
        table = (int []) oTable.clone(); // this makes an exact copy of the table
        out.writeString("Using exchange sort: ");
        t1 = System.currentTimeMillis();
        exchSort();
        t2 = System.currentTimeMillis();
        out.writeLong(t2-t1);
        out.writeString(" milliseconds");
        out.newLine();
        
        table = (int []) oTable.clone(); // this makes an exact copy of the table
        out.writeString("Using merge sort: ");
        t1 = System.currentTimeMillis();
        mergeSort();
        t2 = System.currentTimeMillis();
        out.writeLong(t2-t1);
        out.writeString(" milliseconds");
        out.newLine();
        
        out.close();
        
    }; // constructor
    
    
    /** This method sorts a table into ascending order using selection sort.     */
    
    private void selSort ( ) {
        
        int smallPos;
        int temp;
        
        for ( int i=0 ; i<table.length-1 ; i++ ) {
            smallPos = i;
            for ( int j=i+1 ; j<table.length ; j++ ) {
                if ( table[j] < table[smallPos] ) {
                    smallPos = j;
                };
            };
            temp = table[i];
            table[i] = table[smallPos];
            table[smallPos] = temp;
        };
        
    }; // selSort
    
    
    /** This method sorts a table into ascending order using insertion sort.     */
    
    private void insSort ( ) {
        
        int temp;
        int j;
        
        for ( int i=table.length-2 ; i>=0 ; i-- ) {
            temp = table[i];
            j = i + 1;
            while ( j<table.length && table[j] < temp ) {
                table[j-1] = table[j];
                j = j + 1;
            };
            table[j-1] = temp;
        };
        
    }; // insSort
    
    
    /** This method sorts a table into ascending order using exchange sort.      */
    
    private void exchSort ( ) {
        
        int temp;
        
        for ( int i=table.length-1 ; i>=1 ; i-- ) {
            for ( int j=0 ; j<i ; j++ ) {
                if ( table[j+1] < table[j] ) {
                    temp = table[j];
                    table[j] = table[j+1];
                    table[j+1] = temp;
                };
            };
        };
        
    }; // exchSort
    
    
    /** This method sorts a table into ascending order using merge sort.         */
    
    private void mergeSort ( ) {
        
        mSort(table);
        
    }; // mergeSort
    
    
    private void mSort ( int[] toSort ) {
        
        int[]  left;   // left partition
        int[]  right;  // right partition
        int    lp;     // position in left on merge
        int    rp;     // position in right on merge
        
        if ( toSort.length > 1 ) {
            /* partition */
            left = new int[toSort.length/2];
            right = new int[toSort.length-left.length];
            for ( int i=0 ; i<left.length ; i++ ) {
                left[i] = toSort[i];
            };
            for ( int i=0 ; i<right.length ; i++ ) {
                right[i] = toSort[left.length+i];
            };
            /* sort */
            mSort(left);
            mSort(right);
            /* merge */
            lp = 0;
            rp = 0;
            for ( int i=0 ; i<toSort.length ; i++ ) {
                if ( rp >= right.length ) {
                    toSort[i] = left[lp];
                    lp = lp + 1;
                }
                else if ( lp >= left.length ) {
                    toSort[i] = right[rp];
                    rp = rp + 1;
                }
                else if ( left[lp] <= right[rp] ) {
                    toSort[i] = left[lp];
                    lp = lp + 1;
                }
                else {
                    toSort[i] = right[rp];
                    rp = rp + 1;
                };
            };
        };
        
    }; // mSort
    
    
    private void makeTable ( ) {
        
        int i;
        
        oTable = new int[SIZE];
        for ( i=0 ; i<oTable.length ; i++ ) {
            oTable[i] = (int) (SIZE * Math.random());
        };
        
    }; // makeTable
    
    
    public static void main ( String args[] ) { Sorting s = new Sorting(); };
    
    
} // Sorting
