package org.samtar.cms.common.exception;


import jakarta.servlet.http.HttpServletResponse;
import org.samtar.cms.common.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {


    // Custom Errors
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<GenericResponse<Object>> handleAuthErrors(AuthException e){
        return ResponseEntity.status(e.getStatuscode())
                .body(
                        new GenericResponse<Object>(
                                e.getError(),
                                e.getMessage(),
                                e.getStatuscode(),
                                true
                        ));
    }



    // Unknown Errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse<Object>> handleUnknownErrors(Exception e){
        return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND)
                .body(
                        new GenericResponse<Object>(
                                null,
                                e.getMessage(),
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                true
                        ));
    }


}
