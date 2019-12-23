package com.batval.myrent.exception;

/**
 * Error if the user is not found
 * extends of {@link RuntimeException} class
 *
 * @author Baturo Valery
 * @version 1.0
 */
public class UserNotFoundException extends RuntimeException {

    /**
     *Error message
     *
     */
    private static final String USER_NOT_FOUND_EXCEPTION = "User with id doesnt exist! ";
    /**
     *Error message output
     *
     * @param message output
     */
    public UserNotFoundException(String message) {
        super(USER_NOT_FOUND_EXCEPTION + message);
    }
    /**
     *Error message output
     *
     * @param message output
     * @param cause throwable
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
