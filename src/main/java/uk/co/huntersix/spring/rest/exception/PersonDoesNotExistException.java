package uk.co.huntersix.spring.rest.exception;


/**
 * Person does not exists exception
 * @author sraamasubbu
 */
public class PersonDoesNotExistException extends RuntimeException {

    public PersonDoesNotExistException() {
    }

    public PersonDoesNotExistException(String message) {
        super(message);
    }

    public PersonDoesNotExistException(Throwable cause) {
        super(cause);
    }

}
