import java.util.Random;

public class arrayPracDuplicate {
    private char[] C;

    public arrayPracDuplicate() {
        fill();
        duplicate();
        printArray(C);
    }

    private char getRandomChar(char min, char max) {
        Random random = new Random();
        return (char) (random.nextInt(max - min + 1) + min);
    }

    private void duplicate() {
        for (int i = 0; i < C.length; i++) {
            for (int j = i + 1; j < C.length; j++) {
                if (C[i] == C[j]) {
                    C[i] = Character.toUpperCase(C[i]);
                    C[j] = Character.toUpperCase(C[j]);
                }
            }
        }
    }

    private void fill() {
        C = new char[50];

        for (int i = 0; i <= C.length - 1; i++) {
            C[i] = getRandomChar('a', 'z');
        }
    }

    private void printArray(char[] C) {
        for (char c : C) {
            System.out.print(c + " ");
        }
    }

    public static void main(String[] args) {
        new arrayPracDuplicate();
    }
}