package com.bassem.springbootquiz.container.handler;


import com.bassem.springbootquiz.container.exception.BadInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Priority;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bassem Al Mahow
 */
@ControllerAdvice
@Priority(5)
public abstract class InvalidInputExceptionHandler {

    @ExceptionHandler({BadInputException.class})
    @ResponseBody
    public ResponseEntity handleBadInputException(BadInputException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("violations",ex.getErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}