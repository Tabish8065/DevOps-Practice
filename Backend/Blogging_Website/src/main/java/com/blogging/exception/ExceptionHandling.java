package com.blogging.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.blogging.Model.ErrorDetail;

@RestControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorDetail resourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        return new ErrorDetail(exception.getMessage(),
                                webRequest.getDescription(false)
        );
    }

    @ExceptionHandler(BlogAPIException.class)
    public ErrorDetail blogAPIException(BlogAPIException exception, WebRequest webRequest){
        return new ErrorDetail(
            exception.getMessage(),
            webRequest.getDescription(false)
        );
    }

    @ExceptionHandler(Exception.class)
    public ErrorDetail globalExceptionHadling(Exception exception, WebRequest webRequest){
        return new ErrorDetail(
            exception.getMessage(),
            webRequest.getDescription(false)
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getAllErrors()
                .forEach(error -> 
                    errors.put(((FieldError)error).getField(),
                                error.getDefaultMessage())    
                );
        
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }
    

}
