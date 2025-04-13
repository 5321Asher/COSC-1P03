package beginner;

public class power {
    int value;
    public power() {
    
    }
    
    public int power(int a, int b) {
        if (b <= 0) {
            return 1;
        }
        
        return a * power(a, b - 1);
    }
    
    public static void main(String[] args) {
        power p = new power();
        System.out.println(p.power(2, 3));
    }
}