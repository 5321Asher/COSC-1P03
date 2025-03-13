
public class DeBugIt {

    int [] data;

    public DeBugIt() {
        int sum=0;
        data = new int [15];

        for (int i = 0; i <= data.length; i++)
            data[i] = i;
        data[10]=-1;

    }

    
    public static void main(String[] args) {
        new DeBugIt();
        System.out.println("End Debugit");

    }
}
