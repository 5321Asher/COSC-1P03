package codebook;

import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIOutputFile;

import java.util.Random;


public class encoder {
    private final int ASCIISize = 128;
    private final headerNode[] book = new headerNode[ASCIISize];
    
    public encoder() {
        
        for (int i = 0; i < ASCIISize; i++) {
            book[i] = new headerNode();
        }
    }
    
    private void add(headerNode list, int data) {
        node newNode = new node(data);
        node temp = list.next;
        
        if (temp == null) {
            list.next = newNode;
        } else {
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        list.count++;
    }
    
    public void generateCodeBook() {
        final int randomCharNum = 2000;
        
        Random rand = new Random();
        for (int i = 0; i < randomCharNum; i++) {
            int randomChar = rand.nextInt(ASCIISize);
            add(book[randomChar], i);
        }
    }
    
    public void printCodeBook() {
        for (int i = 0; i < ASCIISize; i++) {
            System.out.print(i + ": ");
            node temp = book[i].next;
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }
    }
    
    public void encode() {
        
        ASCIIDataFile in = new ASCIIDataFile("Message.txt");
        ASCIIOutputFile out = new ASCIIOutputFile("encrypted.txt");
        
        Random rand = new Random();
        char currentChar;
        
        while (! in.isEOF()) {
            currentChar = in.readC();
            int ASCIIValue = currentChar;
            
            headerNode header = book[ASCIIValue];
            
            if (header.count > 0) {
                int randomIndex = rand.nextInt(header.count) + 1;
                    node temp = header.next;
                
                for (int i = 1; i < randomIndex; i++) {
                    temp = temp.next;
                }
                out.writeInt(temp.data);
                out.writeC(' ');
                
                    out.writeLine(" ");
                
                
            }
            
        }
        in.close();
        out.close();
    }
    
    public void decode() {
        ASCIIDataFile in = new ASCIIDataFile("encrypted.txt");
        ASCIIOutputFile out = new ASCIIOutputFile("decrypted.txt");
        
        int encodedValue;
        
        while (! in.isEOF()) {
            encodedValue = in.readInt();
            boolean found = false;
            
       
            for (int i = 0; i < ASCIISize; i++) {
                headerNode header = book[i];
                node temp = header.next;
                
                while (temp != null) {
                    if (temp.data == encodedValue) {
                        out.writeC((char) i);
                        found = true;
                        break;
                    }
                    temp = temp.next;
                }
                if (found) {
                    break;
                }
            }
        }
        
        in.close();
        out.close();
    }
    
    
    public static void main(String[] args) {
        encoder cipher = new encoder();
        cipher.generateCodeBook();
        cipher.printCodeBook();
        cipher.encode();
        cipher.decode();
    }
}