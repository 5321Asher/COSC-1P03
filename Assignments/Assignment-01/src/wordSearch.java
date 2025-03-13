import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIDisplayer;

import java.lang.reflect.Array;

/**
 * <p>The Word Search Solver program is designed to locate words in a 25x25 grid based on a
 * predefined word list. It systematically scans the grid in all possible directions: horizontally,
 * vertically, and diagonally (in both forward and reverse directions). The program marks identified
 * words, saving them in a solved grid for clear visualization of results.</p>
 *
 * <p><b>Dependencies:</b></p>
 * <ul>
 *   <li><b>BasicIO.ASCIIDataFile:</b> Provides methods to read input data.</li>
 *   <li><b>BasicIO.ASCIIDisplayer:</b> Renders the solved puzzle in a user-friendly format.</li>
 * </ul>
 *
 * <p><b>Usage Instructions:</b></p>
 * <p>To use this program:</p>
 * <ul>
 *   <li>Ensure the input file `wordsearch.dat` contains the word list (lines 1-21)</li>
 *   <li>and the word search grid (lines 22-46).</li>
 *   <li>Run the program, and it displays the number of words located and the solved grid.</li>
 * </ul>
 *
 * @author Asher Virgona
 * Student # : 8032492
 * @version 1.0
 * @since 2025-01-17
 */
public class wordSearch {

    //List of found words
    String[] foundList = new String[21];
    int foundListIndex = 0;

    //Arrays for the Solved word search, List of words to find, and Unsolved word search.
    char[][] solvedArray;
    char[][] wordList;
    char[][] board;

    //Input and output utilities
    ASCIIDataFile in = new ASCIIDataFile("wordsearch.dat");
    ASCIIDisplayer out = new ASCIIDisplayer(30, 50);

    int time;


    /**
     * constructor initializes arrays, fills the data, and preforms word search.
     */
    public wordSearch() {

        time = 0;
        fillWordList(); //Populate the word list
        fillBoardList(); //Populate the word search
        fillSolved(); //Initialize the solved grid
        long start = System.currentTimeMillis();
        //Iterate through each word, and coordinate and check in all directions on the board.
        for (int i = 0; i < wordList.length; i++) {
            for (int j = 0; j < board.length; j++) {
                for (int k = 0; k < board[i].length; k++) {
                    checkLeftToRight(i, j);
                    checkRightToLeft(i, j);
                    checkUpToDown(i, k);
                    checkDownToUp(i, k);
                    checkLeftToRightDownDiagonal(i, k, j);
                    checkLeftToRightUpDiagonal(i, k, j);


                    checkRightToLeftDownDiagonal(i, k, j);
                    checkRightToLeftUpDiagonal(i, k, j);


                }

            }
        }
        long end = System.currentTimeMillis();
        time = (int) (end - start);
        //Output results
        out.writeLine(foundListIndex + " Words Found");
        printCharArray(solvedArray); //Display solved puzzle
        System.out.println(time + "ms");
    }

    /**
     * Searches for a word diagonally from bottom right to top left.
     *
     * @param currentWord Index of the current word being searched from the word list array.
     * @param currentCol  Current column in the board being searched.
     * @param currentRow  Current row in the board being searched.
     */
    private void checkRightToLeftUpDiagonal(int currentWord, int currentCol, int currentRow) {
        int wordLength = wordList[currentWord].length;
        char[] checkWord = new char[wordLength];

        //Ensure boundaries are not exceeded
        if (currentRow - wordLength < - 1 || currentCol - wordLength < - 1) {
            return;
        }

        //Iterate through diagonal elements
        for (int i = 0; i < board.length - wordLength; i++) {
            for (int j = 0; j < wordLength; j++) {
                if (board[currentRow - j][currentCol - j] == wordList[currentWord][j]) {
                    checkWord[j] = wordList[currentWord][j];
                    if (j == wordLength - 1) {
                        saveWord(checkWord); //Save the word if found

                        //Mark found word in solved array
                        for (int h = 0; h < wordLength; h++) {
                            solvedArray[currentRow - h][currentCol - h] = wordList[currentWord][h];
                        }
                    }
                } else {
                    checkWord = new char[wordLength];
                    break; //Exit if there is a mismatch
                }
            }
        }
    }

    /**
     * Searches for a word diagonally from top right to bottom left.
     *
     * @param currentWord Index of the current word being searched from the word list array.
     * @param currentCol  Current column in the board being searched.
     * @param currentRow  Current row in the board being searched.
     */
    private void checkRightToLeftDownDiagonal(int currentWord, int currentCol, int currentRow) {
        int wordLength = wordList[currentWord].length;
        char[] checkWord = new char[wordLength];

        //Ensure boundaries are not exceeded
        if (currentRow + wordLength > board.length || currentCol - wordLength < - 1) {
            return;
        }

        //Iterate through diagonal elements
        for (int i = 0; i < board.length - wordLength; i++) {
            for (int j = 0; j < wordLength; j++) {
                if (board[currentRow + j][currentCol - j] == wordList[currentWord][j]) {
                    checkWord[j] = wordList[currentWord][j];
                    if (j == wordLength - 1) {
                        saveWord(checkWord); //Save the word if found

                        //Mark found word in solved array
                        for (int h = 0; h < wordLength; h++) {
                            solvedArray[currentRow + h][currentCol - h] = wordList[currentWord][h];
                        }
                    }
                } else {
                    checkWord = new char[wordLength];
                    break; //Exit if there is a mismatch
                }
            }
        }

    }

    /**
     * Searches for a word diagonally from top left to bottom right.
     *
     * @param currentWord Index of the current word being searched from the word list array.
     * @param currentCol  Current column in the board being searched.
     * @param currentRow  Current row in the board being searched.
     */
    private void checkLeftToRightDownDiagonal(int currentWord, int currentCol, int currentRow) {
        int wordLength = wordList[currentWord].length;
        char[] checkWord = new char[wordLength];

        //Ensure boundaries are not exceeded
        if (currentRow + wordLength > board.length || currentCol + wordLength > board[0].length) {
            return;
        }

        //Iterate through diagonal elements
        for (int i = 0; i < board.length - wordLength; i++) {
            for (int j = 0; j < wordLength; j++) {
                if (board[currentRow + j][currentCol + j] == wordList[currentWord][j]) {
                    checkWord[j] = wordList[currentWord][j];
                    if (j == wordLength - 1) {
                        saveWord(checkWord); //Save the word if found

                        //Mark found word in solved array
                        for (int h = 0; h < wordLength; h++) {
                            solvedArray[currentRow + h][currentCol + h] = wordList[currentWord][h];

                        }

                    }

                } else {
                    checkWord = new char[wordLength];
                    break; //Exit if there is a mismatch

                }
            }

        }
    }

    /**
     * Searches for a word diagonally from bottom left to top right.
     *
     * @param currentWord Index of the current word being searched from the word list array.
     * @param currentCol  Current column in the board being searched.
     * @param currentRow  Current row in the board being searched.
     */
    private void checkLeftToRightUpDiagonal(int currentWord, int currentCol, int currentRow) {
        int wordLength = wordList[currentWord].length;
        char[] checkWord = new char[wordLength];

        //Ensure boundaries are not exceeded
        if (currentRow + wordLength > board.length || currentCol + wordLength > board[0].length) {
            return;
        }

        //Iterate through diagonal elements
        for (int i = 0; i < board.length - wordLength; i++) {
            for (int j = 0; j < wordLength; j++) {
                if (currentRow - 1 >= 0) {
                    if (board[currentRow - j][currentCol + j] == wordList[currentWord][j]) {
                        checkWord[j] = wordList[currentWord][j];
                        if (j == wordLength - 1) {
                            saveWord(checkWord); //Save the word if found

                            //Mark found word in solved array
                            for (int h = 0; h < wordLength; h++) {
                                solvedArray[currentRow - h][currentCol + h] = wordList[currentWord][h];

                            }

                        }

                    } else {
                        checkWord = new char[wordLength];
                        break; //Exit if there is a mismatch

                    }
                } else {
                    return;
                }
            }
        }
    }

    /**
     * Searches for a word vertically from down to up.
     *
     * @param currentWord Index of the current word being searched from the word list array.
     * @param currentCol  Current column being searched.
     */
    private void checkDownToUp(int currentWord, int currentCol) {
        int wordLength = wordList[currentWord].length;
        char[] checkWord = new char[wordLength];

        //Iterate through vertical elements
        for (int i = board.length - 1; i >= wordLength; i--) {
            for (int j = wordLength - 1; j >= 0; j--) {
                if (board[i - j][currentCol] == wordList[currentWord][j]) {
                    checkWord[j] = wordList[currentWord][j];
                    if (j == 0) {
                        saveWord(checkWord); //Save the word if found

                        //Mark found word in solved array
                        for (int h = wordLength - 1; h >= 0; h--) {
                            solvedArray[i - h][currentCol] = wordList[currentWord][h];

                        }

                    }

                } else {
                    checkWord = new char[wordLength];
                    break; //Exit if there is a mismatch

                }
            }

        }
    }




    /**
     * Searches for word vertically from up to down.
     *
     * @param currentWord Index of the current word being searched from the word list array.
     * @param currentCol  Current column being searched.
     */
    private void checkUpToDown(int currentWord, int currentCol) {
        int wordLength = wordList[currentWord].length;
        char[] checkWord = new char[wordLength];

        //Iterate through vertical elements
        for (int i = 0; i < board.length - wordLength; i++) {
            for (int j = 0; j < wordLength; j++) {
                if (board[i + j][currentCol] == wordList[currentWord][j]) {
                    checkWord[j] = wordList[currentWord][j];
                    if (j == wordLength - 1) {
                        saveWord(checkWord); //Save the word if found

                        //Mark found word in solved array
                        for (int h = 0; h < wordLength; h++) {
                            solvedArray[i + h][currentCol] = wordList[currentWord][h];

                        }

                    }

                } else {
                    checkWord = new char[wordLength];
                    break; //Exit if there is a mismatch

                }
            }

        }
    }

    /**
     * Searches for a word horizontally from right to left.
     *
     * @param currentWord Index of the current word being searched from the word list array.
     * @param currentRow  Current row being searched.
     */
    private void checkRightToLeft(int currentWord, int currentRow) {
        int wordLength = wordList[currentWord].length;
        char[] checkWord = new char[wordLength];

        //Iterate through horizontal elements
        for (int i = board.length - 1; i >= wordLength - 1; i--) {
            for (int j = wordLength - 1; j >= 0; j--) {
                if (board[currentRow][i - j] == wordList[currentWord][j]) {
                    checkWord[j] = wordList[currentWord][j];
                    if (j == 0) {
                        saveWord(checkWord); //Save the word if found

                        //Mark found word in solved array
                        for (int h = 0; h < wordLength; h++) {
                            solvedArray[currentRow][i - h] = wordList[currentWord][h];

                        }

                    }

                } else {
                    checkWord = new char[wordLength];
                    break; //Exit if there is a mismatch

                }
            }

        }
    }

    /**
     * Searches for a word horizontally from left to right.
     *
     * @param currentWord Index of the current word being searched from the word list array.
     * @param currentRow  Current row being searched.
     */
    private void checkLeftToRight(int currentWord, int currentRow) {
        int wordLength = wordList[currentWord].length;
        char[] checkWord = new char[wordLength];

        //Iterate through horizontal elements
        for (int i = 0; i < board.length - wordLength; i++) {
            for (int j = 0; j < wordLength; j++) {
                if (board[currentRow][i + j] == wordList[currentWord][j]) {
                    checkWord[j] = wordList[currentWord][j];
                    if (j == wordLength - 1) {
                        saveWord(checkWord); //Save the word if found

                        //Mark found word in solved array
                        for (int h = 0; h < wordLength; h++) {
                            solvedArray[currentRow][i + h] = wordList[currentWord][h];

                        }

                    }

                } else {
                    checkWord = new char[wordLength];
                    break; //Exit if there is a mismatch

                }
            }

        }
    }

    /**
     * Saves a found word to the found list, ensuring no duplicates.
     *
     * @param checkWord The word that was found.
     */
    private void saveWord(char[] checkWord) {
        String foundWord = new String(checkWord);

        //Check if the word is already in the found list
        boolean alreadyFound = false;
        for (String c : foundList) {
            if (foundWord.equals(c)) {
                alreadyFound = true;
                break;
            }
        }

        //Add the word to the found list if not already there
        if (! alreadyFound) {
            foundList[foundListIndex++] = foundWord;

        }
    }

    /**
     * Initializes the solved array with blank chars.
     *
     * @return Initialized solved array.
     */
    private char[][] fillSolved() {

        solvedArray = new char[25][25];

        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                solvedArray[i][j] = (char) 32; //Fill space with black char
            }
        }

        return solvedArray;
    }

    /**
     * Reads the puzzle board from the input file.
     *
     * @return 2D array representing the board.
     */
    private char[][] fillBoardList() {
        String[] boardStringList = new String[25];

        //Read lines from the file
        for (int i = 0; i < 25; i++) {
            boardStringList[i] = in.readString();
        }

        //Convert each line to char array
        board = new char[25][25];
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                board[i][j] = boardStringList[i].toLowerCase().charAt(j);
            }
        }

        return board;
    }

    /**
     * Reads list of words to search from the input file.
     *
     * @return 2D ragged array representing the word list.
     */
    private char[][] fillWordList() {
        String[] wordStringList = new String[21];

        //Read lines from the file
        for (int i = 0; i < wordStringList.length; i++) {
            wordStringList[i] = in.readString();
        }

        //Convert each word to a char array
        wordList = new char[21][];
        for (int i = 0; i < wordList.length; i++) {
            wordList[i] = new char[wordStringList[i].length()];
            for (int j = 0; j < wordList[i].length; j++) {
                wordList[i][j] = wordStringList[i].charAt(j);
            }
        }


        return wordList;
    }

    /**
     * Displays a 2Dc char array using ASCIIDisplayer
     *
     * @param A The array to print.
     */
    private void printCharArray(char[][] A) {
        for (char[] chars : A) {
            for (char aChar : chars) {
                out.writeChar(aChar);
            }
            out.writeLine(" ");
        }
    }

    /**
     * Main method to run the word search program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new wordSearch();

    }

}