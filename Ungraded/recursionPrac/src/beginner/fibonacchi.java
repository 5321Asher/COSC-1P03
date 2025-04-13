package beginner;

public class fibonacchi {
    private int[] fibarr = new int[100];
    int value;
    
    
    public fibonacchi() {
    
    
    }
    
    
    private int fibo(int n) {
        if (n <= 1) {
            return n;
        }
        
        return fibo(n-1) + fibo(n-2);
    }
    
    
    public static void main(String[] args) {
        fibonacchi f = new fibonacchi();
        for (int i = 0; i < 10; i++) {
            System.out.print(f.fibo(i) + " ");
        }
    }
}