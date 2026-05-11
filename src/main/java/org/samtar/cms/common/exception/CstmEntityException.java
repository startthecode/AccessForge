package org.samtar.cms.common.exception;

import org.springframework.http.HttpStatus;

public class CstmEntityException extends ExceptionPattern{
    public CstmEntityException(String message, Object error, HttpStatus statuscode) {
        super(message, error, statuscode);
    }


    public CstmEntityException(String message, HttpStatus statuscode) {
        super(message, null, statuscode);
    }

    public static CstmEntityException EntityValidationFail(String msg) {
        return new CstmEntityException(msg,null, HttpStatus.NOT_FOUND);
    }
}
