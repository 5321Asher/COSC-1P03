package PartB;

import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIOutputFile;

public class PartB {
    private char[][] blocked;
    private int height;
    private int width;
    private final ASCIIOutputFile display = new ASCIIOutputFile("out.txt");
    private int startX;
    private int startY;
    
    
    public PartB() {
        loadMaze();
        start();
        solveMaze(startX, startY);
        blocked[startX][startY] = 'S';
        printMaze();
    }
    
    private void start() {
        startX = (int) (Math.random() * height);
        startY = (int) (Math.random() * width);
        
        if (blocked[startX][startY] == ' ') {
            System.out.println("starting position " + startX + " " + startY);
        } else {
            start();
        }
        
    }
    
    private void loadMaze() {
        ASCIIDataFile maze = new ASCIIDataFile("mz2.txt");
        height = maze.readInt();
        width = maze.readInt();
        System.out.println(height + " " + width);
        blocked = new char[height][width];
        for (int i = 0; i < height; i++) {
            String line = maze.readLine();
            for (int j = 0; j < width && j < line.length(); j++) {
                blocked[i][j] = line.charAt(j);
            }
        }
    }
    
    private boolean solveMaze(int x, int y) {
        if (blocked[x][y] == 'E') {
            System.out.println("you win");
            return true;
        }
        
        if  (x >= height || y >= width) {
            return false;
        }
        
        if (blocked[x][y] == '#' || blocked[x][y] == '.' || blocked[x][y] == '^' || blocked[x][y] == 'v' || blocked[x][y] == '<' || blocked[x][y] == '>' || blocked[x][y] == 'S') {
            return false;
        }
        
        blocked[x][y] = '<';
        if (solveMaze(x, y - 1)) {
            return true;
        }
        
        blocked[x][y] = 'V';
        if (solveMaze(x + 1, y)) {
            return true;
        }
        
        blocked[x][y] = '>';
        if (solveMaze(x, y + 1)) {
            return true;
        }
        
        blocked[x][y] = '^';
        if (solveMaze(x - 1, y)) {
            return true;
        }
        
        blocked[x][y] = '.';
        return false;
    }
    
    private void printMaze() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                display.writeC(blocked[i][j]);
            }
            display.writeLine(" ");
        }
    }
    
    public static void main(String[] args) {
        new PartB();
    }
}