package org.samtar.cms.common.exception;

import org.springframework.http.HttpStatus;

public abstract class ExceptionPattern extends RuntimeException {
    public HttpStatus statuscode;
    public Object error;

    public ExceptionPattern(String message, Object error, HttpStatus statuscode) {
        super(message);
        this.error = error;
        this.statuscode = statuscode;
    }


    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }


    public HttpStatus getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(HttpStatus  statuscode) {
        this.statuscode = statuscode;
    }

}
