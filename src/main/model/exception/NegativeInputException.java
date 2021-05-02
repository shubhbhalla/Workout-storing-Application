package model.exception;

// Checked exception thrown when a negative value is input
public class NegativeInputException extends Exception {
    public NegativeInputException(String str) {
        super(str);
    }
}
