package PartB; //defines package

import BasicIO.ASCIIDataFile; //for reading maze in
import BasicIO.ASCIIOutputFile; //for printing solved maze


/**
 * This program takes a data file (maze) solves the maze using case checking and recursive methods, and prints the
 * solved maze at the end. It requires the brock.jar library and a txt file to run. It also creates a file called
 * out.txt on every run, if already in folder, it overwrites.
 * <p>
 * Asher Virgona
 * 8032492
 */
public class PartB {
    private char[][] blocked; //to check chars for valid positions
    private int height;
    private int width;
    private final ASCIIOutputFile display = new ASCIIOutputFile("out.txt"); //output solved maze
    private int startX;
    private int startY;
    
    /**
     * loads the maze into an array, gets random start coordinates, and starts the solving process. it then prints the
     * solved maze
     */
    public PartB() {
        loadMaze();
        start();
        solveMaze(startX, startY);
        blocked[startX][startY] = 'S'; //starting marker
        printMaze();
    }
    
    /**
     * gets random x and y coordinates for the start position. if the position == # or E, (!=' ') it generates new
     * position
     */
    private void start() {
        startX = (int) (Math.random() * height);
        startY = (int) (Math.random() * width);
        
        if (blocked[startX][startY] == ' ') {
            System.out.println("starting position " + startX + " " + startY);
        } else {
            start(); //recursive calls the method until valid position is found
        }
        
    }
    
    /**
     * loads the maze text file into an X*Y array, X and Y must be found on the first line of they text file seperated
     * by a tab and the maze directly underneath
     */
    private void loadMaze() {
        ASCIIDataFile maze = new ASCIIDataFile("mz2.txt"); //load txt file
        height = maze.readInt(); //first int on txt
        width = maze.readInt(); //second in seperated by first int with a tab
        System.out.println(height + " " + width); //make sure valid array size
        blocked = new char[height][width];
        for (int i = 0; i < height; i++) {
            String line = maze.readLine(); //read whole row of array
            for (int j = 0; j < width && j < line.length(); j++) {
                blocked[i][j] = line.charAt(j); //convert row into chars
            }
        }
    }
    
    /**
     * This method solves the maze using recursive logic. it tests each direction and returns true if direction is
     * valid, the directions are represented using ASCII < (left), > (right), ^ (up), V (down).if any of the cases such
     * as # or . are seen it returns false. if no directions are valid, the method prints a '.' and returns false. if
     * current coordinate == E the program ends as you have found the exit.
     *
     * @param x the X coordinate to check for a valid direction/position
     * @param y the Y coordinate to check for a valid direction/position
     * @return returns true if the position is valid, false if the position is invalid.
     */
    private boolean solveMaze(int x, int y) {
        //win case
        if (blocked[x][y] == 'E') {
            System.out.println("you win");
            return true;
        }
        
        //edge case
        if (x >= height || y >= width) {
            return false;
        }
        
        //valid position
        if (blocked[x][y] == '#' || blocked[x][y] == '.' || blocked[x][y] == '^' || blocked[x][y] == 'v' || blocked[x][y] == '<' || blocked[x][y] == '>' || blocked[x][y] == 'S') {
            return false;
        }
        
        //left
        blocked[x][y] = '<';
        if (solveMaze(x, y - 1)) {
            return true;
        }
        
        //down
        blocked[x][y] = 'V';
        if (solveMaze(x + 1, y)) {
            return true;
        }
        
        //right
        blocked[x][y] = '>';
        if (solveMaze(x, y + 1)) {
            return true;
        }
        
        //up
        blocked[x][y] = '^';
        if (solveMaze(x - 1, y)) {
            return true;
        }
        
        //no valid positions
        blocked[x][y] = '.';
        return false;
    }
    
    /**
     * Prints array to output file, used to print the final solved maze to output file.
     */
    private void printMaze() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                display.writeC(blocked[i][j]);
            }
            display.writeLine(" ");
        }
    }
    
    /**
     * serves as an entry point for the program, it initializes and runs the PartB constructor
     *
     * @param args not used
     */
    public static void main(String[] args) {
        new PartB();
    }
}