package MazeAndSolver;

import Media.*;
import java.awt.*;

public class MazeSolver {
    private final boolean[][] maze;
    private final boolean[][] visited = new boolean[30][30]; // Track visited cells
    private final Turtle pen;
    private final TurtleDisplayer canvas;
    private final int startX, startY;
    private final int endX, endY;
    
    public MazeSolver(boolean[][] maze, Turtle pen, TurtleDisplayer canvas, int startX, int startY, int endX, int endY) {
        this.maze = maze;
        this.pen = pen;
        this.canvas = canvas;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.pen.setPenColor(Color.RED); // Set color to red for solution path
    }
    
    public boolean solve() {
        return solveMaze(startX, startY);
    }
    
    private boolean solveMaze(int x, int y) {
        // Base cases: Out of bounds or not a valid path
        if (x < 0 || x >= 30 || y < 0 || y >= 30 || !maze[x][y] || visited[x][y]) {
            return false;
        }
        
        // Mark the cell as visited
        visited[x][y] = true;
        travel(x, y);
        
        // If exit is reached, return true
        if (x == endX && y == endY) {
            return true;
        }
        
        // Try moving in all directions
        if (solveMaze(x, y - 1) ||  // Move North
                solveMaze(x, y + 1) ||  // Move South
                solveMaze(x + 1, y) ||  // Move East
                solveMaze(x - 1, y)) {  // Move West
            return true;
        }
        
        // Backtrack if no solution found
        visited[x][y] = false;
        return false;
    }
    
    private void travel(int x, int y) {
        pen.moveTo(x * 10 - 145, y * 10 - 145);
    }
}
