import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIOutputFile;
import arrangement.doArrg;
import arrangement.myArrangement;

public class MS {
    public static void main(String[] args) {
        doArrg arr = new doArrg();
        myArrangement r = new myArrangement("Asher's Test Arrangement", 69.3);
        String file = "C:/Users/asher/IdeaProjects/COSC-1P03/Ungraded/flowerManagmentSystem/src/arrangements.txt";
        ASCIIDataFile in = new ASCIIDataFile(file);
        ASCIIOutputFile out = new ASCIIOutputFile(file);
        
        arr.open(in, r, out);
    }
}