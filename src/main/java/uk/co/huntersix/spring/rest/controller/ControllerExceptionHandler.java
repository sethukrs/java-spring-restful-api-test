package uk.co.huntersix.spring.rest.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import uk.co.huntersix.spring.rest.exception.PersonAlreadyExistsException;
import uk.co.huntersix.spring.rest.exception.PersonDoesNotExistException;

/**
 * Controller Exception Handler
 * @author sraamasubbu
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(PersonDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleProfileNotFoundException(PersonDoesNotExistException e) {
        LOGGER.error("Person does not exist", e);
    }

    @ExceptionHandler(PersonAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public void handleUserNameAlreadyExistsException(PersonAlreadyExistsException e) {
        LOGGER.error("Person already exists", e);
    }
}
