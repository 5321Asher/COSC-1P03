package String_T; //defines package

/**
 * The stringTException class is a custom unchecked exception that extends the RuntimeException.
 * This exception is used to handle error scenarios specific to the operations in the custom string class 'myStringT'.
 * It provides the ability to include a detailed error message when thrown.
 *
 * Asher Virgona
 * 8032492
 */
public class stringTException extends RuntimeException {
    
    /**
     * Constructs a new stringTException instance with the specified error message.
     * This message provides a detailed description of the exception.
     *
     * @param message the error message explaining the reason for the exception.
     */
    public stringTException(String message) {
        super(message);
    }
}