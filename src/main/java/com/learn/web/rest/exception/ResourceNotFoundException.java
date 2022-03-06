package com.learn.web.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -539378475930741220L;

    private static final String EXCEPTION_MESSAGE = "Resource not found with ID : %s";
    private static final String MESSAGE = "Resource not found";

    public ResourceNotFoundException() {
        super(MESSAGE);
    }

    public ResourceNotFoundException(String username) {
        super(String.format("Resource not found with Username : %s", username));
    }


    public ResourceNotFoundException(Long id) {
        super(String.format(EXCEPTION_MESSAGE, id));
    }

}
