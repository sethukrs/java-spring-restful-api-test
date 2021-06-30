package uk.co.huntersix.spring.rest.exception;

/**
 * Person already exists exception
 * @author sraamasubbu
 */
public class PersonAlreadyExistsException extends RuntimeException {

    public PersonAlreadyExistsException() {
    }

    public PersonAlreadyExistsException(String message) {
        super(message);
    }

    public PersonAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
