package com.maheshtambelabs.dairymgmt.productservice.exception;


/**
 * @author Mahesh Tambe
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
