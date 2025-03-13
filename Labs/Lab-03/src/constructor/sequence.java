package constructor;

public class sequence {
    public sequence() {
        IntNode head = new IntNode(5, null);
        head = new IntNode(8, head);
        head.next.next = new IntNode(5, new IntNode(5, new IntNode(0, null)));
    }

    private IntNode genList(int[] arr) {
        for (int idx = arr.length - 1; idx >= 0; idx--) {
            System.out.println(arr[idx]);
        }
        return null; //Clearly a placeholder}
    }

    private void printIntList( IntNode ptr ) {
        while (ptr!=null) {
            System.out.print("\t" + ptr.item);
            ptr = ptr.next;
        }
        System.out.println();
    }


}