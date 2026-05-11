package org.samtar.cms.common.exception;

import org.springframework.http.HttpStatus;

public class AuthorityException extends ExceptionPattern {

    public AuthorityException(String message, HttpStatus statuscode, Object error) {
        super(message, error, statuscode);
    }

    public AuthorityException(String message, HttpStatus statuscode) {

        super(message,
                null,
                statuscode);
    }

    public static AuthorityException authorityNotFound() {
        return new AuthorityException("Authority not found",
                HttpStatus.NOT_FOUND,
                null);
    }


    
}
