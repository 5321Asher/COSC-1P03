import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIOutputFile;
import arrangement.myArrangement;
import minor.myFlower;

public class MS {
	public static void main(String[] args) {
		myArrangement blah = new myArrangement("current", 17);
		ASCIIDataFile in = new ASCIIDataFile();
		ASCIIOutputFile out = new ASCIIOutputFile();
		ASCIIDataFile in2 = new ASCIIDataFile();
		
		blah.list();
		blah.load(in);
		System.out.println(blah.getName());
		System.out.println(blah.getPrice());
		System.out.println();
		blah.listItems();
		System.out.println();
		
		
	}
}