package com.springboot.boilerplate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;

@ControllerAdvice
public class ControllerAdvisor {

  @ExceptionHandler(AppException.class)
  public ResponseEntity handleCustomHttpExceptions(AppException appException) {
    return new ResponseEntity<>(new HashMap<String, Object>() {{
      put("message", appException.getMessage());
      put("errors", appException.getErrors());
    }}, appException.getStatus());
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity handleHttpArgsNotMatchException(MethodArgumentTypeMismatchException exception) {
    return new ResponseEntity<>(new HashMap<String, String>() {{
      put("message", String.format("Invalid value:= %s for param:= %s", exception.getValue(), exception.getName()));
    }}, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity handleException(Exception exception) {
    return new ResponseEntity<>(new HashMap<String, String>() {{
      put("message", exception.getMessage());
    }}, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
