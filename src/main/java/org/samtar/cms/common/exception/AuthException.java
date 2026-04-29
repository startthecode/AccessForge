package org.samtar.cms.common.exception;

import org.springframework.http.HttpStatus;
import org.samtar.cms.modules.shared.enums.TokenTypes;
import org.springframework.http.HttpStatus;

public class AuthException extends ExceptionPattern{
    public AuthException(String message, HttpStatus statuscode,Object error) {
        super(message,error,statuscode);
    }
    public AuthException(String message, HttpStatus statuscode) {
        super(message,null,statuscode);
    }
    public static AuthException InvalidCredentials(String message,Object error){
        return new AuthException( message,  HttpStatus.BAD_REQUEST, error);
    }

    public static AuthException UserNotFound(String message ){
        return new AuthException( message, HttpStatus.NOT_FOUND);
    }

    public static AuthException userAlreadyExists(String message ){
        return new AuthException( message,  HttpStatus.CONFLICT);
    }

    public static AuthException tokenExpired(TokenTypes type){
        return new AuthException( type + " token has been expired",  HttpStatus.BAD_GATEWAY);
    }
    public static AuthException InvalidToken(TokenTypes type){
        return new AuthException( type +  " token is invalid",  HttpStatus.BAD_GATEWAY);
    }

    public static  AuthException sessionExpired() {
        return new AuthException("Session expired. Please login again",HttpStatus.BAD_GATEWAY);
    }

    public static  AuthException missingAuthHeaders() {
        return new AuthException("Missing or malformed Authorization header",HttpStatus.UNAUTHORIZED);
    }
}
