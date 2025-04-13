package beginner;

public class digitSum {
    public digitSum() {
    
    }
    
    public int doSum(int n) {
        if (n <= 0) {
            return 0;
        }
        
        n = n % 10;
        
        return n + doSum(n - 1);
    }
    
    
    public static void main(String[] args) {
        digitSum d = new digitSum();
        System.out.println(d.doSum(123456789));
    }
}
