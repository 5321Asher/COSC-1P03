package textConstructor;

public class textStitcher {
    private StringNode head;
    private StringNode tail;
    private int size;

    public textStitcher() {
        tail = head = new StringNode(null, null);
    }

    public textStitcher(String initial) {
        this();
        append(initial);
    }

    public void append(String text) {
        tail.next = new StringNode(text, null);
        tail = tail.next;
        size += text.length(); //Useful for memory allocation later!

    }

    public void prepend(String text) {
        head.next = new StringNode(text, head.next);
        if (tail == head) {
            tail = head.next;
        }
        size += text.length();
    }

    public int getLength() {
        return size;
    }

    public String toString() {
        char[] result = new char[size];
        int index = 0;
        StringNode current = head.next;

        while (current != null) {
            char[] chars = current.item.toCharArray();
            for (char c : chars) {
                result[index++] = c;
            }
            current = current.next;
        }

        return new String(result);
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        textStitcher stitcher = new textStitcher("0");

        // Append and prepend strings
        for (int i = 1; i <= 100000; i++) {
            stitcher.append(" ");
            stitcher.append(Integer.toString(i));
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Final stitched string: " + stitcher.toString());
        System.out.println("Total length: " + stitcher.getLength());
        System.out.println("time: " + (endTime - startTime) + "ms");

    }

}
