package com.example.librarymanagementsystem.advice;

import com.example.librarymanagementsystem.exceptin.MismatchedDataException;
import com.example.librarymanagementsystem.exceptin.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
         Map<String,String> errorMap = new HashMap<>();
         exception.getBindingResult().getFieldErrors().forEach((error) -> {
             errorMap.put(error.getField(), error.getDefaultMessage());
         });
         return errorMap;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ObjectNotFoundException.class, MismatchedDataException.class})
    public Map<String,String> handleMismatchDataAndObjectNotFound(Exception exception) {
        Map<String,String> errorMap = new HashMap<>();
            errorMap.put("errorMassage", exception.getMessage());
        return errorMap;
    }
}
