package org.samtar.cms.common.exception;

import org.springframework.http.HttpStatus;

public class PermissionException extends ExceptionPattern {
    public PermissionException(String message, HttpStatus statuscode, Object error) {
        super(message, error, statuscode);
    }

    public PermissionException(String message, HttpStatus statuscode) {
        super(message,
                null,
                statuscode);
    }

    public static PermissionException accessDenied() {
        return new PermissionException("User dont have a access to performe this action",
                HttpStatus.UNAUTHORIZED,
                null);
    }


}

