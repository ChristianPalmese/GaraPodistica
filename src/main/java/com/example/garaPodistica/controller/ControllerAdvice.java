package com.example.garaPodistica.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> gestioneErrore(MethodArgumentNotValidException ex){
        List<String> stringList=ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField()+": "+fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        Map<String, List<String>> hashMap= new HashMap<>();
        hashMap.put("errors", stringList);
        return new ResponseEntity<>(hashMap,new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
