package com.pquinci.ualabackendchallenge.controller;

import com.mysql.cj.exceptions.DataTruncationException;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import com.pquinci.ualabackendchallenge.exception.UserAlreadyFollowedException;
import com.pquinci.ualabackendchallenge.exception.UserNotFoundExcdeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MysqlDataTruncation.class)
    protected ResponseEntity<String> getError(MysqlDataTruncation ex){
        return new ResponseEntity<String>("Error: Texto de Tweet muy extenso " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundExcdeption.class)
    protected ResponseEntity<String> userException(UserNotFoundExcdeption ex){
        return new ResponseEntity<String>("Error: Los usuarios que quiere seguir o consultar no existen", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserAlreadyFollowedException.class)
    protected ResponseEntity<String> userAlreadyException(UserAlreadyFollowedException ex){
        return new ResponseEntity<String>("Error: Ud ya sigue al usuario solicitado", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
