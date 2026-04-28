package org.samtar.cms.common.response;

public class GenericResponse<T> {
    private int statusCode;
    private String message;
    private T data;
    private T error;

    public GenericResponse(T data, String message, int statusCode,boolean isError) {
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

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
