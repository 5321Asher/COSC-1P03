


public class Lab2_Searching {

    final int NumSearches = 1;    //Number of times we search for a number
    final int DataSize = 500;     //Size of the data array
    static final int NOT_FOUND = - 1; //Used in Binary Search

    int[] data;     //The data array that we will search

    public Lab2_Searching() {
        int FindThisNumber;   //A random number we will search for.
        int Probes;           // Count of the number of Probes it took to FindthisNumber

        data = new int[DataSize];   //create an load data
        for (int i = 0; i < data.length; i++)
            data[i] = i;


        Probes = 0;
        for (int i = 0; i < NumSearches; i++) {
            FindThisNumber = (int) (Math.random() * DataSize);
            Probes += FindLinear(FindThisNumber);
        }
        System.out.println(Probes / NumSearches + " ");


        Probes = 0;
        for (int i = 0; i < NumSearches; i++) {
            FindThisNumber = (int) (Math.random() * DataSize);
            Probes += FindBinary(data, FindThisNumber);
        }
        System.out.println(Probes / NumSearches + " ");


    }


    //Binary search returning number of probes on a success
    private int FindBinary(int[] a, int x) {

        int low = 0;
        int high = a.length - 1;
        int Probes = 0;
        while (low <= high) {
            Probes++;
            int mid = (low + high) / 2;
            if (x < a[mid]) {
                high = mid - 1;
            } else if (x > a[mid]) {
                low = mid + 1;
            } else {
                return Probes;
            }

        }
        return NOT_FOUND;
    }

    //Lenear search, returning the number of Probes for a successful search.
    private int FindLinear(int FindThisNumber) {
        int probes = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == FindThisNumber) {
                return probes + 1;
            }
            probes++;
        }
        return NOT_FOUND;

    }

    public static void main(String[] args) {
        new Lab2_Searching();
        System.out.println("End Searching");

    }

}
