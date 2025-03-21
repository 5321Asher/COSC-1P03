package PartA;

import Media.*;

public class PartA {
    
    Turtle pen = new Turtle(0);
    TurtleDisplayer display = new TurtleDisplayer(pen, 400, 400);
    
    public PartA() {
        pen.penDown();
        display.waitForUser();
        for (int i = 0; i < 7; i++) {
            branch(10, 75);
            pen.left(2 * Math.PI / 7);
        }
        
        display.close();
    }
    
    private void branch(int order, int len) {
        if (order <= 0) {
            return;
        }
        
        if (order == 10) {
            // Special pattern for main branches
            pen.forward(len);
            
            // Create branches at the end
            pen.right(Math.PI / 6);
            branch(order - 1, len / 2);
            branch(order - 1, len / 4);
            
            pen.left(Math.PI / 6);
            
            pen.left(Math.PI / 6);
            branch(order - 1, len / 2);
            branch(order - 1, len / 4);
            
            pen.right(Math.PI / 6);
            
            
            pen.backward(len / 3);  // Go back to middle
            
            pen.right(Math.PI / 6);
            branch(order - 1, len / 2);
            branch(order - 1, len / 4);
            pen.left(Math.PI / 6);
            
            pen.left(Math.PI / 6);
            branch(order - 1, len / 2);
            
            branch(order - 1, len / 4);
            pen.right(Math.PI / 6);
            
            pen.backward(len / 3);
            
            // Create additional inner branches
              // Go back to middle
            
            
            pen.right(Math.PI / 6);
            branch(order - 1, len / 2);
            branch(order - 1, len / 4);
            pen.left(Math.PI / 6);
            
            pen.left(Math.PI / 6);
            branch(order - 1, len / 2);
            
            branch(order - 1, len / 4);
            pen.right(Math.PI / 6);
            
            pen.backward(len / 3);  // Go all the way back
            
            pen.forward(len / 3 + len);
            pen.right(Math.PI / 6);
            branch(order - 1, len / 2);
            branch(order - 1, len / 4);
            pen.left(Math.PI / 6);
            
            pen.left(Math.PI / 6);
            branch(order - 1, len / 2);
            
            branch(order - 1, len / 4);
            pen.right(Math.PI / 6);
            
            pen.backward(len / 3 + len);
            
            pen.forward(2*(len / 3) + len);
            pen.right(Math.PI / 6);
            
            branch(order - 1, len / 5);
            pen.left(Math.PI / 6);
            
            pen.left(Math.PI / 6);
            
            
            branch(order - 1, len / 5);
            pen.right(Math.PI / 6);
            
            pen.backward(2*(len / 3) + len);
        } else {
            // Regular pattern for smaller branches
            pen.forward(len);
            
            pen.right(Math.PI / 6);
            branch(order - 1, len / 2);
            branch(order - 1, len / 4);
            
            pen.left(Math.PI / 6);
            
            pen.left(Math.PI / 6);
            branch(order - 1, len / 2);
            branch(order - 1, len / 4);
            
            pen.right(Math.PI / 6);
            
            pen.backward(len);
        }
    }
    
    
    public static void main(String[] args) {
        new PartA();
    }
}