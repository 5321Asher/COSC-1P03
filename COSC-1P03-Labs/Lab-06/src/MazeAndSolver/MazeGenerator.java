package MazeAndSolver;

import Media.Turtle;
import Media.TurtleDisplayer;

import java.awt.*;
import java.util.Random;

public class MazeGenerator {
    private final boolean[][] maze = new boolean[30][30]; // Stores open paths
    private final Turtle pen;
    private final TurtleDisplayer canvas;
    private final int startX, startY; // Random start position
    private int endX, endY;     // Random exit position
    
    public MazeGenerator() {
        pen = new Turtle(0);
        canvas = new TurtleDisplayer(pen, 300, 300);
        setupCanvas();
        
        Random rand = new Random();
        startX = rand.nextInt(30); // Random start position
        startY = rand.nextInt(30);
        
        do {
            endX = rand.nextInt(30); // Random exit position
            endY = rand.nextInt(30);
        } while (startX == endX && startY == endY); // Ensure start != exit
        
        canvas.waitForUser();
        generateMaze(startX, startY);
    }
    
    private void setupCanvas() {
        pen.setPenWidth(400);
        pen.penDown();
        pen.moveTo(- 100, 100);
        pen.moveTo(100, 100);
        pen.moveTo(100, - 100);
        pen.moveTo(- 100, - 100);
        pen.moveTo(- 100, 100);
        pen.penUp();
        pen.setPenWidth(5);
        pen.setPenColor(Color.WHITE);
    }
    
    private void travel(int x, int y) {
        pen.moveTo(x * 10 - 145, y * 10 - 145);
    }
    
    private char[] randomDirections() {
        char[] directions = {'N', 'S', 'E', 'W'};
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            int swapIdx = rand.nextInt(4);
            char temp = directions[i];
            directions[i] = directions[swapIdx];
            directions[swapIdx] = temp;
        }
        return directions;
    }
    
    private void generateMaze(int x, int y) {
        if (x < 0 || x >= 30 || y < 0 || y >= 30 || maze[x][y]) {
            return;
        }
        
        maze[x][y] = true; // Mark cell as open
        travel(x, y);
        pen.penDown();
        
        char[] directions = randomDirections();
        for (char dir : directions) {
            switch (dir) {
                case 'N':
                    generateMaze(x, y - 1);
                    break;
                case 'S':
                    generateMaze(x, y + 1);
                    break;
                case 'E':
                    generateMaze(x + 1, y);
                    break;
                case 'W':
                    generateMaze(x - 1, y);
                    break;
            }
            travel(x, y); // Backtrack to previous position
        }
    }
    
    public boolean[][] getMaze() {
        return maze;
    }
    
    public int getStartX() {
        return startX;
    }
    
    public int getStartY() {
        return startY;
    }
    
    public int getEndX() {
        return endX;
    }
    
    public int getEndY() {
        return endY;
    }
    
    public Turtle getTurtle() {
        return pen;
    }
    
    public TurtleDisplayer getCanvas() {
        return canvas;
    }
    
    public static void main(String[] args) {
        MazeGenerator generator = new MazeGenerator();
        MazeSolver solver = new MazeSolver(generator.getMaze(), generator.getTurtle(), generator.getCanvas(), generator.getStartX(), generator.getStartY(), generator.getEndX(), generator.getEndY());
        solver.solve();
    }
}
