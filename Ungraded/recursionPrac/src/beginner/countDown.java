package beginner;

public class countDown {
    public countDown() {
        
    }
    
    public int count(int n) {
        if (n > 0) {
            System.out.println(n);
            count(n - 1);
        } else if (n <= 0) {
            System.out.println("blast");
            return 0;
        }
        return n;
    }
    
    
    public static void main(String[] args) {
        countDown obj = new countDown();
        obj.count(5);
    }
}