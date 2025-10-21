package co.edu.ean.poo.tests;

public class FailedTestException extends RuntimeException {
    public FailedTestException(String message) {
        super(message);
    }
    public FailedTestException(String message, Throwable cause) {
        super(message, cause);
    }
}
