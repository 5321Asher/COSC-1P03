package beginner;

public class factorial {
    
    public factorial() {
    
    }
    
    int value;
    public int factorial(int n) {
        if (n == 0) return 1;
        
        value = n * factorial(n - 1);
        
        return value;
    }
    
    
    public static void main(String[] args) {
        factorial f = new factorial();
        System.out.println(f.factorial(5));    
    }
}