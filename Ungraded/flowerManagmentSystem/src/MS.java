import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIDisplayer;
import BasicIO.ASCIIOutputFile;
import arrangement.doArrg;
import arrangement.myArrangement;
import catalog.doCataItem;
import catalog.itemCatalog;

public class MS {
    public static void main(String[] args) {
        /*doArrg arr = new doArrg();
        myArrangement r = new myArrangement("Asher's Test Arrangement", 69.3, "rose.png");
        
        
        //myArrangement r = new myArrangement("test", 100.1);
        
        ASCIIDataFile in = new ASCIIDataFile(r.getLoadFile());
        ASCIIOutputFile out = new ASCIIOutputFile(r.getLoadFile());
        arr.open(in, r, out);*/
        
        doCataItem cata = new doCataItem();
        itemCatalog r = new itemCatalog("Item Catalog", "Item");
        
        cata.openCataItem(r);
        
    }
}