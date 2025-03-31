public class exersize implements soda {
    
    private int[] syrup;
    private int size = 0;
    private int kind = 0;
    
    public exersize(int[] n) {
        syrup = n;
    }
    
    @Override
    public void setKind(int c) {
        kind = c;
    }
    
    @Override
    public void setSize(int c) {
        size = c;
    }
    
    @Override
    public boolean dispense() {
        if (syrup[kind] >= size + 1) {
            syrup[kind] -= size + 1;
            return true;
        }
        
        return false;
    }
    
    public String toString() {
        String update = "";
        
        for (int i = 0; i < syrup.length; i++) {
            update += Integer.toString(syrup[i]) + " ";
        }
        
        return update;
    }
}
