package test; //defines package

import String_T.StringT;
import String_T.myStringT;
import String_T.stringTException;
import String_T.stringTIterator;

import java.util.Scanner;

/**
 * The {@code testStringT} class provides methods to test operations
 * on the {@code StringT} Abstract Data Type (ADT). It includes a
 * variety of operations to validate the functionality of the
 * underlying implementation of {@code StringT}.
 * <p>
 * The class assumes interaction with the user through the console
 * for some test cases and makes use of the {@code Scanner} class
 * for input reading.
 * <p>
 * Asher Virgona
 * 8032492
 */
public class testStringT {
    
    /**
     * Tests the functionality of the ConCat method in the StringT interface.
     * This method validates the correctness of concatenating two StringT objects
     * by comparing the result with the expected character array output.
     *
     * @param S The primary StringT object on which the ConCat method will be tested.
     */
    public void testConCat(StringT S) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a second string to concatenate: ");
        String secondInput = scanner.nextLine(); //takes second string
        StringT String2 = new myStringT(secondInput.toCharArray());
        
        StringT result = S.ConCat(String2);
        char[] expected = new char[S.Length() + String2.Length()];
        
        for (int i = 0; i < S.Length(); i++) { //copies first string
            expected[i] = S.CharAt(i);
        }
        for (int i = 0; i < String2.Length(); i++) { //copies second string
            expected[S.Length() + i] = String2.CharAt(i);
        }
        
        //checks if StringT method and manual method match (test)
        boolean match = true;
        char[] resultArray = result.ToArray();
        for (int i = 0; i < resultArray.length; i++) {
            if (resultArray[i] != expected[i]) {
                match = false;
                break;
            }
        }
        System.out.println(result.ToArray()); //print test result
        System.out.println("testConCat: " + (match ? "Passed" : "Failed"));
    }
    
    /**
     * Tests the functionality of the Before method in the StringT interface.
     * This method validates the correctness of obtaining a substring from the
     * start of the StringT object up to a specified index by comparing the
     * result with the expected character array output.
     *
     * @param S The StringT object on which the Before method will be tested.
     */
    public void testBefore(StringT S) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an index for Before test: ");
        int index = scanner.nextInt(); //takes before index
        
        StringT result = S.Before(index);
        char[] expected = new char[index];
        
        for (int i = 0; i < index; i++) { //manual array testing
            expected[i] = S.CharAt(i);
        }
        
        //checks manual test vs StringT method
        boolean match = true;
        char[] resultArray = result.ToArray();
        for (int i = 0; i < resultArray.length; i++) {
            if (resultArray[i] != expected[i]) {
                match = false;
                break;
            }
        }
        System.out.println(result.ToArray()); //output test result
        System.out.println("testBefore: " + (match ? "Passed" : "Failed"));
    }
    
    /**
     * Tests the functionality of the After method in the StringT interface.
     * This method validates the correctness of obtaining a substring from a specified index
     * to the end of the StringT object by comparing the result with the expected character array output.
     *
     * @param S The StringT object on which the After method will be tested.
     */
    public void testAfter(StringT S) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an index for After test: ");
        int index = scanner.nextInt(); //takes after index
        
        StringT result = S.After(index);
        char[] expected = new char[S.Length() - index];
        
        for (int i = index, j = 0; i < S.Length(); i++, j++) { //manual array testing
            expected[j] = S.CharAt(i);
        }
        
        //checks manual test vs StringT method
        boolean match = true;
        char[] resultArray = result.ToArray();
        for (int i = 0; i < resultArray.length; i++) {
            if (resultArray[i] != expected[i]) {
                match = false;
                break;
            }
        }
        System.out.println(result.ToArray()); //test result
        System.out.println("testAfter: " + (match ? "Passed" : "Failed"));
    }
    
    /**
     * Tests the functionality of the Length method in the StringT interface.
     * This method validates the correctness of the string length returned by the Length method
     * by comparing it with the length of the character array obtained from the ToArray method.
     *
     * @param S The StringT object on which the Length method will be tested.
     */
    public void testLength(StringT S) {
        int result = S.Length();
        System.out.println("String length: " + result); //test result
        System.out.println("testLength: " + (result == S.ToArray().length ? "Passed" : "Failed"));
    }
    
    /**
     * Tests the functionality of the Clone method in the StringT interface.
     * This method validates that the Clone method creates a deep copy of the StringT object.
     * It checks that the cloned object is not the same instance as the original,
     * but contains identical data as a character array.
     *
     * @param S The StringT object on which the Clone method will be tested.
     */
    public void testClone(StringT S) {
        StringT result = S.Clone();
        System.out.println(result.ToArray()); //test result
        System.out.println("testClone: " + (S != result && new String(S.ToArray()).equals(new String(result.ToArray())) ? "Passed" : "Failed"));
    }
    
    /**
     * Tests the functionality of the CharAt method in the StringT interface.
     * This method verifies if the CharAt method correctly returns the character
     * at the specified index in a StringT object by comparing it with the expected character from the ToArray method.
     * If the index is out of bounds, it catches and handles the stringTException.
     *
     * @param S The StringT object on which the CharAt method will be tested.
     */
    public void testCharAt(StringT S) {
        Scanner scann = new Scanner(System.in);
        System.out.println("enter an index to test");
        int i = scann.nextInt(); //takes char input
        
        try { //tries to compare StringT method vs S.ToArray()[i]
            char result = S.CharAt(i);
            System.out.println(result);
            System.out.println("testCharAt: " + (result == S.ToArray()[i] ? "Passed" : "Failed"));
        } catch (stringTException e) { //chathes exception in charAt method
            System.out.println("testCharAt: Failed with exception: " + e.getMessage());
        }
    }
    
    /**
     * Tests the functionality of the ToArray method in the StringT interface.
     * This method validates the correctness of converting the StringT object into a character array
     * by comparing it with an expected array generated using the CharAt and Length methods.
     *
     * @param S The StringT object on which the ToArray method will be tested.
     */
    public void testToArray(StringT S) {
        char[] original = S.ToArray(); //StringT char array
        char[] expected = new char[S.Length()]; //manual array test array
        
        for (int i = 0; i < S.Length(); i++) { //fills manual test
            expected[i] = S.CharAt(i);
        }
        
        //compares StringT vs Manual test
        boolean match = true;
        for (int i = 0; i < original.length; i++) {
            if (original[i] != expected[i]) {
                match = false;
                break;
            }
        }
        System.out.println(original); //test result
        System.out.println("testToArray: " + (match ? "Passed" : "Failed"));
    }
    
    /**
     * Tests the functionality of the SubString method in the StringT interface.
     * This method validates the correctness of extracting a substring from a StringT object
     * by comparing the result with an expected substring derived from a character array.
     * If the indices are invalid, it catches and handles the stringTException.
     *
     * @param S The StringT object on which the SubString method will be tested.
     */
    public void testSubString(StringT S) {
        Scanner scann = new Scanner(System.in);
        System.out.println("enter an index to start at");
        int i = scann.nextInt(); //takes start index
        System.out.println("enter another index to end at");
        int j = scann.nextInt(); //takes end index
        
        try { //tries StringT method vs manual test (String offset)
            StringT result = S.SubString(i, j);
            System.out.println(result.ToArray());
            System.out.println("testSubString: " + (new String(result.ToArray()).equals(new String(S.ToArray(), i, j - i)) ? "Passed" : "Failed"));
        } catch (stringTException e) { //catches exception from StringT method
            System.out.println("testSubString: Failed with exception: " + e.getMessage());
        }
    }
    
    /**
     * Tests the functionality of the replaceChar method in the StringT interface.
     * This method validates whether the replaceChar method correctly replaces
     * all occurrences of a specified character in the StringT object with a given
     * replacement character. It compares the resulting StringT object with an
     * expected character array to determine the correctness of the operation.
     *
     * @param S The StringT object on which the replaceChar method will be tested.
     */
    public void testReplace(StringT S) {
        Scanner scann = new Scanner(System.in);
        System.out.println("enter a character to replace");
        char c = scann.next().charAt(0); //target char
        System.out.println("enter a character to replace with");
        char r = scann.next().charAt(0); //replacement char
        StringT result = S.replaceChar(c, r);
        
        char[] expected = new char[S.Length()]; //manual array test
        for (int i = 0; i < S.Length(); i++) {
            if (S.CharAt(i) == c) {
                expected[i] = r;
            } else {
                expected[i] = S.CharAt(i);
            }
        }
        
        // comapre StringT method vs manual array test
        boolean match = true;
        for (int i = 0; i < expected.length; i++) {
            if (expected[i] != result.CharAt(i)) {
                match = false;
                break;
            }
        }
        System.out.println(result.ToArray()); //test result
        System.out.println("testReplace: " + (match ? "Passed" : "Failed"));
    }
    
    /**
     * Tests the functionality of the startsWith method in the StringT interface.
     * This method validates whether a StringT object starts with a given prefix
     * provided by the user. It uses both the startsWith method and a manual comparison
     * to ensure the correctness of the result.
     *
     * @param S The StringT object to be tested for the specified prefix.
     */
    public void testStartsWith(StringT S) {
        Scanner scann = new Scanner(System.in);
        System.out.println("enter a prefix to test the String");
        StringT prefix = new myStringT(scann.nextLine().toCharArray());
        
        boolean result = S.startsWith(prefix); //StringT method
        boolean manualResult = false;
        
        //manual method
        int count = 0;
        for (int i = 0; i < prefix.Length(); i++) {
            if (S.CharAt(i) != prefix.CharAt(i)) {
                manualResult = false;
                break;
            }
            count++;
        }
        if (count == prefix.Length()) { //true check
            manualResult = true;
        }
        if (! manualResult || ! result) { //if either is false somehow, the whole test turns false
            manualResult = false;
            result = false;
        }
        
        System.out.println("ADT result: " + result); //StringT result
        System.out.println("Manual Result: " + manualResult); //manual result
        System.out.println("testStartsWith: " + (result ? "Passed" : "Failed")); //test result
    }
    
    /**
     * Tests the functionality of the iterator over a StringT object using the for-each loop.
     * This method validates that the iteration over the characters of the StringT object
     * produces the correct sequence by comparing it with the character array returned
     * by the ToArray method.
     *
     * @param S The StringT object whose iterator is being tested.
     */
    public void testIteratorForEach(StringT S) {
        char[] expected = S.ToArray();
        int index = 0;
        boolean match = true;
        
        for (char c : S.ToArray()) {
            if (c != expected[index]) {
                match = false;
                break;
            }
            index++;
        }
        System.out.println("testIteratorForEach: " + (match ? "Passed" : "Failed"));
    }
    
    /**
     * Tests the functionality of the iterator's next() method in the stringTIterator class.
     * This method validates if the iterator correctly iterates through all characters
     * of a StringT object in sequence by comparing the output with the character array
     * returned by the ToArray method.
     *
     * @param S The StringT object whose iterator's next() method is being tested.
     */
    public void testIteratorNext(StringT S) {
        stringTIterator iterator = new stringTIterator((myStringT) S); // Use the iterator
        char[] expected = S.ToArray();
        int index = 0;
        boolean match = true;
        
        while (iterator.hasNext()) {
            if (iterator.next() != expected[index]) {
                match = false;
                break;
            }
            index++;
        }
        System.out.println("testIteratorNextHasNext: " + (match ? "Passed" : "Failed"));
    }
    
    /**
     * The main method serves as the entry point for the program.
     * It provides a menu-based interface for the user to run various tests
     * on the implementation of the StringT interface using the testStringT class.
     *
     * The user is prompted to input a string, choose a test case from the menu,
     * and the corresponding test is executed. Multiple test cases can be executed in succession
     * until the program is terminated.
     *
     * @param args Command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        testStringT tester = new testStringT();
        
        Scanner scann = new Scanner(System.in);
        System.out.println("Enter a string to test with");
        String input = scann.nextLine(); //takes test String for all StringT methods
        StringT testString = new myStringT(input.toCharArray());
        
        while (true) {
            //menu for testing
            System.out.println("please choose a test case");
            System.out.println("1. Concatenation");
            System.out.println("2. Before");
            System.out.println("3. After");
            System.out.println("4. Length");
            System.out.println("5. Clone");
            System.out.println("6. CharAt");
            System.out.println("7. ToArray");
            System.out.println("8. SubString");
            System.out.println("9. Replace");
            System.out.println("10. StartsWith");
            System.out.println("11. Iterator");
            System.out.println("12. Test Suite (ALL TESTS)");
            
            Scanner scann2 = new Scanner(System.in);
            int choice = scann2.nextInt(); //takes test choice
            
            //switch case executes test methods
            switch (choice) {
                case 1:
                    tester.testConCat(testString);
                    System.out.println(" ");
                    break;
                
                case 2:
                    tester.testBefore(testString);
                    System.out.println(" ");
                    break;
                
                case 3:
                    tester.testAfter(testString);
                    System.out.println(" ");
                    break;
                
                case 4:
                    tester.testLength(testString);
                    System.out.println(" ");
                    break;
                
                case 5:
                    tester.testClone(testString);
                    System.out.println(" ");
                    break;
                
                case 6:
                    tester.testCharAt(testString);
                    System.out.println(" ");
                    break;
                
                case 7:
                    tester.testToArray(testString);
                    System.out.println(" ");
                    break;
                
                case 8:
                    tester.testSubString(testString);
                    System.out.println(" ");
                    break;
                
                case 9:
                    tester.testReplace(testString);
                    System.out.println(" ");
                    break;
                
                case 10:
                    tester.testStartsWith(testString);
                    System.out.println(" ");
                    break;
                
                case 11:
                    tester.testIteratorForEach(testString);
                    tester.testIteratorNext(testString);
                    System.out.println(" ");
                    
                    break;
                    
                //tests all methods in succsession instead of one at a time
                case 12:
                    tester.testConCat(testString);
                    tester.testBefore(testString);
                    tester.testAfter(testString);
                    tester.testLength(testString);
                    tester.testClone(testString);
                    tester.testCharAt(testString);
                    tester.testToArray(testString);
                    tester.testSubString(testString);
                    tester.testReplace(testString);
                    tester.testStartsWith(testString);
                    tester.testIteratorForEach(testString);
                    tester.testIteratorNext(testString);
                    System.out.println(" ");
                    break;
                
                default:
                    System.out.println("invalid choice, try again");
            }
        }
    }
}
