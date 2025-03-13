package String_T; //defines package

import java.util.Iterator; // imports the iterator to iterate over characters

/**
 * Represents an immutable string-like object that provides various operations
 * for string manipulation, such as concatenation, substring extraction, length determination,
 * character access, and character replacement. The string is internally represented by a character array
 * and ensures immutability by creating defensive copies during operations.
 *
 * This class implements the {@code StringT} interface and provides functionality to work
 * with string data in an immutable way while adhering to the contract of the methods defined in the interface.
 * It also supports iteration over its characters by implementing the {@code Iterable<Character>} interface.
 *
 * Asher Virgona
 * 8032492
 */

/**
 * Represents an immutable string with various methods to manipulate and retrieve
 * information about the string. This implementation ensures immutability by storing
 * the string as a character array and providing operations that return new instances
 * of the class.
 */
public class myStringT implements StringT, Iterable<Character> {
    private final char[] chars; // stores the chars of the input string in an immutable array
    
    /**
     * Constructs a new instance of the myStringT class using the provided character array.
     *
     * @param input the character array used to initialize the instance. Cannot be null.
     * @throws stringTException if the input array is null.
     */
    public myStringT(char[] input) {
        if (input == null) {
            throw new stringTException("Null input not allowed");
        }
        
        chars = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            chars[i] = input[i];
        }
    }
    
    /**
     * Constructs a new instance of the myStringT class with an empty character array.
     * This default constructor initializes the instance with no characters.
     */
    public myStringT() {
        chars = new char[0];
    }
    
    /**
     * Concatenates this StringT object with the provided StringT, resulting in a new StringT
     * that represents the combination of the two strings.
     *
     * @param S the StringT to concatenate with this StringT. Cannot be null.
     * @return a new StringT containing the concatenated contents of this StringT and the provided StringT.
     * @throws stringTException if the provided StringT is null.
     */
    @Override
    public StringT ConCat(StringT S) {
        if (S == null) {
            throw new stringTException("Cannot concatenate null");
        }
        
        char[] otherChars = S.ToArray();
        char[] newChars = new char[chars.length + otherChars.length];
        for (int i = 0; i < chars.length; i++) { //copy of first StringT
            newChars[i] = chars[i];
        }
        for (int i = 0; i < S.Length(); i++) { //copy of second StringT
            newChars[i + chars.length] = otherChars[i];
        }
        
        return new myStringT(newChars);
    }
    
    /**
     * Returns a new StringT containing the characters from the start of this StringT
     * up to, but not including, the specified index.
     *
     * @param i the index up to which characters are included in the resulting StringT.
     *          Must be non-negative and less than the length of this StringT.
     * @return a new StringT consisting of the characters from the start of this StringT
     *         up to the specified index.
     * @throws stringTException if the provided index is out of bounds (i.e., less than 0
     *         or greater than or equal to the length of this StringT).
     */
    @Override
    public StringT Before(int i) {
        if (i < 0 || i >= chars.length) {
            throw new stringTException("Index out of bounds");
        }
        
        char[] newChars = new char[i];
        for (int j = 0; j < i; j++) { //from start of StringT to i
            newChars[j] = chars[j];
        }
        
        return new myStringT(newChars);
    }
    
    /**
     * Returns a new StringT consisting of the characters from the specified index to the end
     * of the current StringT.
     *
     * @param i the starting index from which characters are included in the resulting StringT.
     *          Must be non-negative and less than the length of this StringT.
     * @return a new StringT containing the characters from the specified index to the end of this StringT.
     * @throws stringTException if the provided index is out of bounds (i.e., less than 0
     *                          or greater than or equal to the length of this StringT).
     */
    @Override
    public StringT After(int i) {
        if (i < 0 || i >= chars.length) {
            throw new stringTException("Index out of bounds");
        }
        
        char[] newChars = new char[chars.length - i];
        for (int j = i, k = 0; j < chars.length; j++, k++) { //from i to end of StringT
            newChars[k] = chars[j];
        }
        
        return new myStringT(newChars);
    }
    
    /**
     * Returns the number of characters in the current instance of the string.
     *
     * @return the length of the string represented by this instance.
     */
    @Override
    public int Length() {
        return chars.length;
    }
    
    /**
     * Creates and returns a deep clone of this StringT.
     *
     * @return a new StringT instance that is a deep copy of the current StringT object.
     */
    @Override
    public StringT Clone() {
        char[] clonedChars = new char[this.chars.length];
        for (int i = 0; i < this.chars.length; i++) {
            clonedChars[i] = this.chars[i];
        }
        
        return new myStringT(clonedChars);
    }
    
    /**
     * Returns the character at the specified index in this StringT.
     * The index must be within the valid range [0, Length() - 1].
     *
     * @param i the index of the character to retrieve. Must be non-negative and less than the length of this StringT.
     * @return the character located at the specified index in this StringT.
     * @throws stringTException if the provided index is out of bounds (less than 0 or greater than or equal to the length of this StringT).
     */
    @Override
    public char CharAt(int i) {
        if (i < 0 || i >= chars.length) {
            throw new stringTException("index out of bounds");
        }
        
        return chars[i];
    }
    
    /**
     * Converts the string represented by this instance into a character array.
     * This method creates a deep copy of the internal character array to ensure
     * the immutability of the original string data.
     *
     * @return a new character array containing the characters of this string.
     */
    @Override
    public char[] ToArray() {
        char[] copy = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            copy[i] = chars[i];
        }
        
        return copy;
    }
    
    /**
     * Returns a new StringT containing the characters between the specified indices.
     * The starting index is inclusive, and the ending index is exclusive.
     *
     * @param i the starting index of the substring, inclusive. Must be non-negative and less than the length of this StringT.
     * @param j the ending index of the substring, exclusive. Must be greater than the starting index, non-negative, and less than or equal to the length of this StringT.
     * @return a new StringT consisting of the characters between the specified indices.
     * @throws stringTException if the provided indices are out of bounds or invalid (e.g., start index is greater than or equal to the end index).
     */
    @Override
    public StringT SubString(int i, int j) {
        if (i < 0 || i >= chars.length || j < 0 || j >= chars.length || i >= j) { //border checking
            throw new stringTException("invalid range");
        }
        
        char[] newChars = new char[j - i];
        for (int k = i; k < j; k++) { // chars between the indices
            newChars[k - i] = chars[k];
        }
        
        return new myStringT(newChars);
    }
    
    /**
     * Replaces all occurrences of a specified character in this StringT object with another character
     * and returns a new StringT instance with the modifications.
     *
     * @param target the character to be replaced in the string. This character will be searched in the internal character array.
     * @param replace the character that will replace the occurrences of the target character.
     * @return a new StringT instance with the specified character replacements applied.
     */
    @Override
    public StringT replaceChar(char target, char replace) {
        char replacedStringT[] = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == target) {
                replacedStringT[i] = replace;
            }
            else {
                replacedStringT[i] = chars[i];
            }
        }
        return new myStringT(replacedStringT);
    }
    
    /**
     * Checks if the current StringT instance starts with the specified prefix StringT.
     *
     * @param S the StringT prefix to check against the start of this StringT. Cannot be null.
     * @return true if the current StringT starts with the specified prefix, false otherwise.
     */
    @Override
    public boolean startsWith(StringT S) {
        char[] prefix = S.ToArray();
        
        if (prefix.length > chars.length || prefix.length <= 0) {
            return false;
        }
        int count = 0;
        
        for (int i = 0; i < prefix.length; i++) {
            if (chars[i] != prefix[i]) {
                return false;
            }
            count++;
        }
        
        boolean result = false;
        if (count == prefix.length) {
            result = true;
        }
        return result;
    }
    
    /**
     * Provides an iterator to traverse the characters in this instance of myStringT.
     * The iteration occurs sequentially from the first character to the last character.
     *
     * @return an Iterator over the characters contained in this myStringT instance.
     */
    @Override
    public Iterator<Character> iterator() {
        return new stringTIterator(this);
    }
}