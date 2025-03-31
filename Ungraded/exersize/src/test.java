public class test {
    
    public static void main (String args[]) {
        int [] a = {1,2,3,4,5};
        
        exersize c = new exersize(a);
        
        System.out.println(c.toString());
        
        c.setKind(2);
        c.setSize(1);
        
        if (c.dispense()) {
            System.out.println(c.toString());
        } else {
            System.out.println("not enough syrup");
        }
    }
    
}
