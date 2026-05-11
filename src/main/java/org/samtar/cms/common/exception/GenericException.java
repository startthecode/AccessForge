package org.samtar.cms.common.exception;

import org.springframework.http.HttpStatus;

public class GenericException extends ExceptionPattern{
    public GenericException(String message, Object error, HttpStatus statuscode) {
        super(message, error, statuscode);
    }


    public GenericException(String message, HttpStatus statuscode) {

        super(message,
                null,
                statuscode);
    }

    public static GenericException genException(String msg) {
        return new GenericException(
                msg,
                null,
                HttpStatus.NOT_ACCEPTABLE
        );
    }
}