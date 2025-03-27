import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIDisplayer;
import BasicIO.ASCIIOutputFile;
import arrangement.doArrg;
import arrangement.myArrangement;

public class MS {
    public static void main(String[] args) {
        doArrg arr = new doArrg();
        myArrangement r = new myArrangement("testDis", 69.3);
        ASCIIDataFile in = new ASCIIDataFile("C:/Users/asher/IdeaProjects/COSC-1P03/Ungraded/flowerManagmentSystem/src/arrangements.txt");
        ASCIIOutputFile out = new ASCIIOutputFile("C:/Users/asher/IdeaProjects/COSC-1P03/Ungraded/flowerManagmentSystem/src/arrangements.txt");
        
        arr.open(in, r, out);
    }
}