public class arrayPracAlphabet {
    char[][] A;

    public arrayPracAlphabet() {
        A = new char[5][5];

        fill();
        printArray(A);
    }

    private void fill() {
        char firstChar = 'a';

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                A[i][j] = (char) (firstChar + j);
            }
            firstChar++;
        }
    }

    private void printArray(char[][] A) {
        for (char[] row : A) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new arrayPracAlphabet();
    }
}