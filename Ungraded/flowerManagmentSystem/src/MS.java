import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIOutputFile;
import arrangement.doArrg;
import arrangement.myArrangement;

public class MS {
    public static void main(String[] args) {
        doArrg arr = new doArrg();
        myArrangement r = new myArrangement("testDis", 69.3);
        ASCIIDataFile in = new ASCIIDataFile();
        //ASCIIOutputFile out = new ASCIIOutputFile();
        
        arr.open(in, r);
    }
}