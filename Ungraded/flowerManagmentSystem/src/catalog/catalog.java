package catalog;

import BasicIO.ASCIIOutputFile;
import arrangement.myArrangement;

public interface catalog {
    
    public void load();
    
    public void save(ASCIIOutputFile out);
    
    public void listStart();
    
    public myArrangement search(String search);
    
    public void addArrangement(myArrangement object);
    
    public void removeArrangement(String delete);
    
    public myArrangement getCurrent();
    
    public myArrangement up();
    
    public myArrangement down();
    
    public myArrangement getFirstArrangement();
}
