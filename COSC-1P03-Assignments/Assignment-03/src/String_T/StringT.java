package String_T;

/**
 * Interface for a custom String ADT (StringT).
 * Defines methods for string manipulation without using Java's built-in String class.
 *
 * Asher Virgona
 * 8032492
 */
public interface StringT {
    
    // Concatenates this StringT with another and returns a new StringT.
    public StringT ConCat(StringT S);
    
    // Returns a substring from index 0 to i (inclusive).
    public StringT Before(int i);
    
    // Returns a substring from index i to the end.
    public StringT After(int i);
    
    // Returns the length of this StringT.
    public int Length();
    
    // Creates and returns a deep clone of this StringT.
    public StringT Clone();
    
    // Returns the character at index i.
    public char CharAt(int i);
    
    // Converts and returns this StringT as a character array.
    public char[] ToArray();
    
    // Returns a substring between indices i (inclusive) and j (exclusive).
    public StringT SubString(int i, int j);
    
    // Two custom methods for additional string operations.
    public StringT replaceChar(char a, char b);
    
    public boolean startsWith(StringT S);
    
}
