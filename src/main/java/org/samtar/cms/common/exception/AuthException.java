package org.samtar.cms.common.exception;

import jakarta.servlet.http.HttpServletResponse;
import org.samtar.cms.modules.shared.enums.TokenTypes;

public class AuthException extends RuntimeException{

    private int statuscode;
    private Object error;

    public AuthException(String message, int statuscode,Object error) {
        super(message);
        this.error = error;
        this.statuscode = statuscode;
    }

    public AuthException(String message, int statuscode) {
        super(message);
        this.statuscode = statuscode;
    }

    public static AuthException InvalidCredentials(String message,Object error){
        return new AuthException( message,  HttpServletResponse.SC_BAD_REQUEST, error);
    }

    public static AuthException UserNotFound(String message ){
        return new AuthException( message, HttpServletResponse.SC_NOT_FOUND);
    }

    public static AuthException userAlreadyExists(String message ){
        return new AuthException( message,  HttpServletResponse.SC_CONFLICT);
    }

    public static AuthException tokenExpired(TokenTypes type){
        return new AuthException( TokenTypes.Access == type ? "Access" : "Refresh" + " token has been",  HttpServletResponse.SC_BAD_GATEWAY);
    }
    public static AuthException InvalidToken(TokenTypes type){
        return new AuthException(  TokenTypes.Access == type ? "Access" : "Refresh" + " token is invalid",  HttpServletResponse.SC_BAD_GATEWAY);
    }

    public static  AuthException sessionExpired() {
        return new AuthException("Session expired. Please login again",HttpServletResponse.SC_BAD_GATEWAY);
    }


    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }


    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }
}
