public class arrayPracTranspose {
    private int[][] A;
    private int[][] B;

    public arrayPracTranspose() {

        A = new int[3][4];
        B = new int[4][3];

        fill();
        flip();
        printArray(A);
        System.out.println();
        printArray(B);
    }

    private void flip() {
        int cur;
        for (int i = 0; i <= A.length - 1; i++) {
            for (int j = 0; j <= A[i].length - 1; j++) {
                cur = A[i][j];
                B[j][i] = cur;

            }
        }
    }

    private void fill() {
        int inc = 0;
        for (int i = 0; i <= A.length - 1; i++) {
            for (int j = 0; j <= A[i].length - 1; j++) {
                A[i][j] = inc;

                inc += 1;
            }
        }
    }

    
    private void printArray(int[][] A) {
        for (int[] row : A) {
            for (int c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new arrayPracTranspose();
    }
}