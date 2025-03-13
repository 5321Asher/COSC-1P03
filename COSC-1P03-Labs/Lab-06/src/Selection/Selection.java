package Selection;

import java.util.Scanner;

public class Selection {
    
    int highestValue;
    
    public Selection() {
        highestValue = 0;
        Scanner input = new Scanner(System.in);
        Node lst = generate(8, 1, 8);
        displayList(lst);
        System.out.println();
        System.out.println("highest value possible is: " + calcHighestValue(lst));
        
        while (true) {
            System.out.print("Target value: ");
            String line = input.nextLine();
            try {
                int choice = Integer.parseInt(line);
                Node selection = choose(choice, lst);
                if (selection == null) {
                    System.out.println("Cannot calculate with given numbers.");
                } else {
                    displayList(selection);
                }
                System.out.println();
            } catch (NumberFormatException nfe) {
                break;
            }
        }
        input.close();
    }
    
    private int calcHighestValue(Node ptr) {
        if (ptr == null) {
            return (0);
        }
        while (ptr != null) {
            highestValue += ptr.item;
            ptr = ptr.next;
        }
        return highestValue;
    }
    
    private void displayList(Node ptr) {
        if (ptr == null) {
            return;
        }
        System.out.print(" " + ptr.item);
        displayList(ptr.next);
    }
    
    private int genInt(int lb, int ub) {
        return (int) (Math.random() * (ub - lb + 1) + lb);
    }
    
    private Node generate(int qty, int lb, int ub) {
        if (qty == 0)
            return null;
        return new Node(genInt(lb, ub), generate(qty - 1, lb, ub));
    }
    
    private Node choose(int target, Node lst) {
        if (lst == null)
            return null;
        
        if (lst.item == target) {
            return new Node(lst.item, null);
        }
        
        Node withCurrent = choose(target - lst.item, lst.next);
        if (withCurrent != null) {
            return new Node(lst.item, withCurrent);
        }
        
        return choose(target, lst.next);
    }
    
    public static void main(String[] args) {
        new Selection();
    }
}
