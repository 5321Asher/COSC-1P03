import BasicIO.ASCIIDisplayer;
import BasicIO.ASCIIPrompter;

import java.util.Arrays;


public class lab1edit {
    ASCIIDisplayer display;
    ASCIIPrompter prompt;

    public lab1edit() {
        display = new ASCIIDisplayer();
        int[][] A = new int[7][7];
        int[][] B = new int[A.length][A[1].length];
        int[][] O = new int[A.length][A[1].length];
        fill(A);
        fillO(O);

        apply(A, B, O);

    }

    private void fillO(int[][] O) {
        for (int i = 0; i < O.length; i++) {
            for (int j = 0; j < O[i].length; j++) {
                O[i][j] = 0;
            }
        }
    }

    private void apply(int[][] A, int[][] B, int[][] O) {
        printArray(A);
        int i = 1;
        int[][] D = new int[A.length][A[1].length];
        int[][] H = new int[A.length][A[1].length];
        while (A != O) {

            if (D == B) {
                break;
            }

            display.writeInt(i);

            if (i % 2 == 1) {
                check(A, B);
                display.writeLine(" ");
                printArray(B);
                if (i % 3 == 0) {
                    H = B;
                }
                if (i%10 == 0) {
                    D = B;
                }
            }
            if (i % 2 == 0) {
                check(B, A);
                display.writeLine(" ");
                printArray(A);
                if (i % 3 == 0) {
                    H = A;
                }
                if (i%10 == 0) {
                    D = A;
                }
            }
            i++;
        }
    }

    private void fill(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                int value = (int) (Math.random() * 100);

                if (value > 50) {
                    A[i][j] = 1;
                }
                if (value <= 50) {
                    A[i][j] = 0;
                }


            }
        }

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

    private void check(int[][] A, int[][] B) {
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

    private void count(int[][] A, int row, int col, int[][] B) {
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
        new lab1edit();
    }
}