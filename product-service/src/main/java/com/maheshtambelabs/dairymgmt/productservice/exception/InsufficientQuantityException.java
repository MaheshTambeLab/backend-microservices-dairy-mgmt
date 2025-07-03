package com.maheshtambelabs.dairymgmt.productservice.exception;

/**
 * @author Mahesh Tambe
 */
public class InsufficientQuantityException extends RuntimeException {
    public InsufficientQuantityException(String message) {
        super(message);
    }
}
