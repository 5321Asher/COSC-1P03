package PartA;

import Media.Turtle;
import Media.TurtleDisplayer;
import java.awt.*;

public class PartA {
    private final boolean[][] visited = new boolean[30][30];
    private final Turtle pen;
    
    public PartA() {
        pen = new Turtle(0);
        TurtleDisplayer canvas = new TurtleDisplayer(pen, 300, 300);
        setupCanvas();
        int startX = (int) (Math.random() * 30);
        int startY = (int) (Math.random() * 30);
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
        for (int i = 0; i < 4; i++) {
            int swapIdx = (int) (Math.random() * 4);
            char temp = directions[i];
            directions[i] = directions[swapIdx];
            directions[swapIdx] = temp;
        }
        return directions;
    }
    
    private void generateMaze(int x, int y) {
        if (x < 0 || x >= 30 || y < 0 || y >= 30 || visited[x][y]) {
            return;
        }
        
        visited[x][y] = true;
        
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
            travel(x, y);
        }
    }
    
    public static void main(String[] args) {
        new PartA();
    }
}