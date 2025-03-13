import BasicIO.ASCIIDisplayer;
import BasicIO.ASCIIPrompter;

import java.util.Arrays;


public class lab1 {
    ASCIIDisplayer display;
    ASCIIPrompter prompt;

    public lab1() {
        display = new ASCIIDisplayer();
        int[][] B = new int [7][7];
        int[][] A = {
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0},
                {0, 0, 1, 1, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 1, 0, 0},
                {0, 0, 1, 1, 0, 1, 0}

        };

        printArray(A);
        check(A, B);
        display.writeLine(" ");
        printArray(B);

    }

    private void apply(int [][] A, int [][] B) {

    }

    private void printArray(int[][] A) {
        for (int[] row : A) {
            for (int c : row) {
                if (c == 0) {
                    display.writeString("O");
                }
                if (c == 1) {
                    display.writeString("x");
                }
            }
            display.writeLine(" ");
        }
    }

    private void check(int[][] A, int [][] B) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {

                count(A, i, j, B);


            }
        }
    }

    private int survive(int[] A) {
        int state = 0;
        int arraySum = 0;

        for (int i = 0; i < A.length; i++) {
            arraySum += A[i];
        }

        if (arraySum == 3 || arraySum == 2) {
            state = 1;
        }


        return state;
    }

    private void count(int[][] A, int row, int col, int [][] B) {
        int[] C = new int[8];

        C[0] = getCell(row - 1, col - 1, A);
        C[1] = getCell(row - 1, col, A);
        C[2] = getCell(row - 1, col + 1, A);
        C[3] = getCell(row, col - 1, A);
        C[4] = getCell(row, col + 1, A);
        C[5] = getCell(row + 1, col - 1, A);
        C[6] = getCell(row + 1, col, A);
        C[7] = getCell(row + 1, col + 1, A);

        String cellState = Arrays.toString(C);


        if (A[row][col] == 0) {
            System.out.println(determine(C));
            B[row][col] = determine(C);
        }

        if (A[row][col] == 1) {
            System.out.println(survive(C));
            B[row][col] = survive(C);
        }
        System.out.println(cellState);



    }

    

    private int getCell(int row, int col, int[][] A) {
        if (row < 0 || col < 0 || row >= A.length || col >= A[0].length)
            return 0;
        return A[row][col];
    }

    private int determine(int[] A) {
        int state = 0;
        int arraySum = 0;

        for (int i = 0; i < A.length; i++) {
            arraySum += A[i];
        }

        if (arraySum == 3) {
            state = 1;
        }


        return state;
    }


    public static void main(String[] args) {
        new lab1();
    }
}