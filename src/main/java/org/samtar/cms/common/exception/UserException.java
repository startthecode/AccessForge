package org.samtar.cms.common.exception;

import jakarta.servlet.http.HttpServletResponse;
import org.samtar.cms.modules.shared.enums.TokenTypes;
import org.springframework.http.HttpStatus;

public class UserException extends ExceptionPattern{

    public UserException(String message, HttpStatus statuscode, Object error) {
        super(message,error,statuscode);
    }

    public UserException(String message, HttpStatus statuscode) {
        super(message,null,statuscode);
    }

    public static UserException userNotFound(){
        return new UserException( "user not found",  HttpStatus.NOT_FOUND, null);
    }

    public static UserException userAccountDeleted(){
        return new UserException( "User account has been already deleted",  HttpStatus.NOT_FOUND, null);
    }

}
