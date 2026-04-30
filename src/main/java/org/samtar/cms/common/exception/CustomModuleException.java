package org.samtar.cms.common.exception;

import org.springframework.http.HttpStatus;

public class CustomModuleException extends ExceptionPattern {

    public CustomModuleException(String message, HttpStatus statuscode, Object error) {
        super(message, error, statuscode);
    }

    public CustomModuleException(String message, HttpStatus statuscode) {
        super(message, null, statuscode);
    }

    public static CustomModuleException moduleNotFound() {
        return new CustomModuleException("custom module not found", HttpStatus.NOT_FOUND, null);
    }

    public static CustomModuleException moduleAlreadyExists() {
        return new CustomModuleException("custom module already exists", HttpStatus.CONFLICT, null);
    }
}
