package String_T; //defines package

import java.util.Iterator; //imports the iterator to iterate over characters


/**
 * An iterator implementation for iterating over characters in a myStringT object.
 * This iterator converts the myStringT instance into a character array and
 * provides sequential access to its elements.
 */

/**
 * The stringTIterator class is an implementation of the Iterator interface,
 * designed specifically for iterating over the characters of a myStringT object.
 *
 * This iterator processes the characters of a myStringT instance sequentially
 * and provides methods to check if there are more characters to iterate over
 * and to retrieve the next character.
 *
 * Asher Virgona
 * 8032492
 */
public class stringTIterator implements Iterator<Character> {
    private final char[] chars;
    private int index;
    
    /**
     * Constructs a stringTIterator for iterating over the characters
     * of the specified myStringT instance.
     *
     * @param stringT the myStringT object whose characters will be iterated over
     */
    public stringTIterator(myStringT stringT) {
        this.chars = stringT.ToArray();
        this.index = 0;
    }
    
    /**
     * Checks if there are more characters to iterate over in the current sequence.
     *
     * @return true if there are remaining characters to iterate; false otherwise.
     */
    @Override
    public boolean hasNext() {
        return index < chars.length;
    }
    
    /**
     * Retrieves the next character in the sequence during iteration.
     *
     * This method advances the iterators position and returns the character
     * currently at that position. If no characters are remaining, an exception
     * is thrown.
     *
     * @return the next character in the sequence
     * @throws IndexOutOfBoundsException if there are no more characters to iterate over
     */
    @Override
    public Character next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException();
        }
        
        return chars[index++];
    }
}