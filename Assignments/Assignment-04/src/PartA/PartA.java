package PartA; //defines package

import Media.*; //imports turtle displayer and turtle

/**
 * this program recursively draws branches which form into a pattern. depending on certain variables these patterns can
 * change easily. these variables include the order, length, and rotation values.
 * Asher Virgona
 * 8032492
 */
public class PartA {
    Turtle pen = new Turtle(0); //to draw
    TurtleDisplayer display = new TurtleDisplayer(pen, 400, 400); //to draw on
    
    /**
     * execeutes the for loop to indicate the amount of instances and rotation between each instance the pattern will
     * repeat
     */
    public PartA() {
        pen.penDown();
        display.waitForUser();
        for (int i = 0; i < 7; i++) { //7 instances
            branch(10, 75);
            pen.left(2 * Math.PI / 7); //PI/7 rotation
        }
        display.close();
    }
    
    /**
     * the branch method recursively calls itself to branch off until the order of the original call <= 0. the order is
     * decremented each recursive call, and the length of the branch is split anyway each recursive call.
     * @param order the amount of branches left in the original method call
     * @param len the length of the current branch/ length of next call branch
     */
    private void branch(int order, int len) {
        // base case, tells the program to stop if there are no more branches to be drawn
        if (order <= 0) {
            return;
        }
        
        //if no branches have taken place yet (speacial case for inner pattern)
        if (order == 10) {
            //regular outer patter first
            pen.forward(len);
            
            pen.right(Math.PI / 6);
            branch(order - 1, len / 2);
            branch(order - 1, len / 4);
            
            pen.left(Math.PI / 6); //recenter
            
            pen.left(Math.PI / 6);
            branch(order - 1, len / 2);
            branch(order - 1, len / 4);
            
            pen.right(Math.PI / 6); //recenter
            
            
            //special pattern for inner snowflake
            pen.backward(len / 3);
            
            pen.right(Math.PI / 6);
            branch(order - 1, len / 2);
            branch(order - 1, len / 4);
            
            pen.left(Math.PI / 6); //recenter
            
            pen.left(Math.PI / 6);
            branch(order - 1, len / 2);
            branch(order - 1, len / 4);
            
            pen.right(Math.PI / 6); //recenter
            
            //go back another third of len
            pen.backward(len / 3);
            
            pen.right(Math.PI / 6);
            branch(order - 1, len / 2);
            branch(order - 1, len / 4);
            
            pen.left(Math.PI / 6); //recenter
            
            pen.left(Math.PI / 6);
            branch(order - 1, len / 2);
            branch(order - 1, len / 4);
            
            pen.right(Math.PI / 6); //recenter
            
            //all the way back
            pen.backward(len / 3);
            
            //special patter for outer snowflake
            pen.forward(len / 3 + len);
            
            pen.right(Math.PI / 6);
            branch(order - 1, len / 2);
            branch(order - 1, len / 4);
            
            pen.left(Math.PI / 6); //recenter
            
            pen.left(Math.PI / 6);
            branch(order - 1, len / 2);
            branch(order - 1, len / 4);
            
            pen.right(Math.PI / 6); //recenter
            
            //all the way back
            pen.backward(len / 3 + len);
            
            //even further out the snoflake, outskirts of the pattern
            pen.forward(2*(len / 3) + len);
            
            pen.right(Math.PI / 6);
            branch(order - 1, len / 5);
            
            pen.left(Math.PI / 6); //recenter
            
            pen.left(Math.PI / 6);
            branch(order - 1, len / 5);
            
            pen.right(Math.PI / 6); //recenter
            
            //all the way back
            pen.backward(2*(len / 3) + len);
        } else {
            //regular recursive call if it's not the stem/first branch
            pen.forward(len);
            
            pen.right(Math.PI / 6);
            branch(order - 1, len / 2);
            branch(order - 1, len / 4);
            
            pen.left(Math.PI / 6); //recenter
            
            pen.left(Math.PI / 6);
            branch(order - 1, len / 2);
            branch(order - 1, len / 4);
            
            pen.right(Math.PI / 6); //recenter
            
            //all the way back
            pen.backward(len);
        }
    }
    
    /**
     * serves as an entry point into the program, it initializes and runs the PartA constructor
     * @param args not used
     */
    public static void main(String[] args) {
        new PartA();
    }
}