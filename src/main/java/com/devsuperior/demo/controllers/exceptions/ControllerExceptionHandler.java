package com.devsuperior.demo.controllers.exceptions;

import com.devsuperior.demo.services.exceptions.DataBaseException;
import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandartError> entityNotFound(DataBaseException e,
                                                        HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        StandartError error = new StandartError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Resource Not Found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());


        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandartError> database(ResourceNotFoundException e,
                                                        HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        StandartError error = new StandartError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Database Exception");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());


        return ResponseEntity.status(status).body(error);
    }



}
