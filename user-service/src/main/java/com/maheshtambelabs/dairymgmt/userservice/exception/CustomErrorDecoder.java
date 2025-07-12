package com.maheshtambelabs.dairymgmt.userservice.exception;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        // Log the error details if needed
        if (response.status() >= 400 && response.status() <= 499) {
            return new FeignException.NotFound("Product not found", response.request(), null, null);
        }
        if (response.status() >= 500) {
            return new FeignException.InternalServerError("Internal server error", response.request(), null, null);
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}