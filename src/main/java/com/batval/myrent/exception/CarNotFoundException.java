package com.batval.myrent.exception;

/**
 * Error if the car is not found
 * extends of {@link RuntimeException} class
 *
 * @author Baturo Valery
 * @version 1.0
 */
public class CarNotFoundException extends RuntimeException {
    /**
     *Error message
     *
     */
    private static final String CAR_NOT_FOUND_EXCEPTION = "Car with id doesnt exist! ";
    /**
     *Error message output
     *
     * @param message output
     */
    public CarNotFoundException(String message) {
        super(CAR_NOT_FOUND_EXCEPTION + message);
    }
    /**
     *Error message output
     *
     * @param message output
     * @param cause throwable
     */
    public CarNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
