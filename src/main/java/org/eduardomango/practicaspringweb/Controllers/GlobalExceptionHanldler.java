package org.eduardomango.practicaspringweb.Controllers;
import org.eduardomango.practicaspringweb.DTO.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHanldler {


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorDetails> hanlderElementNotFound(NoSuchElementException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    ///podria meter una excepciones de invalid entry


}


