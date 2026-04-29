package org.samtar.cms.common.response;

import org.springframework.http.HttpStatus;

public class GenericResponse<T> {
    private HttpStatus  statusCode;
    private String message;
    private T data;
    private T error;

    public GenericResponse(T data, String message, HttpStatus statusCode, boolean isError) {
        if(isError){
            this.error = data;
        }else {
            this.data = data;
        }
        this.message = message;
        this.statusCode = statusCode;
    }

    public T getError() {
        return error;
    }

    public void setError(T error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus  getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus  statusCode) {
        this.statusCode = statusCode;
    }
}
