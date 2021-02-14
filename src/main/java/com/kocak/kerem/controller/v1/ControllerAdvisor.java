package com.kocak.kerem.controller.v1;

import com.kocak.kerem.exception.NoPersonFoundException;
import com.kocak.kerem.util.ApiConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoPersonFoundException.class)
    public ResponseEntity<Object> handleNoUserFoundException(Exception ex) {
        return new ResponseEntity<>(ApiConstants.NO_USER_FOUND_EXCEPTION, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        log.error("Unknown Exception: {}", ex.getLocalizedMessage());
        log.error("Unknown Exception: {}", (Object) ex.getStackTrace());
        return new ResponseEntity<>(ApiConstants.MASTER_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}