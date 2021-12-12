package com.springboot.boilerplate.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
public class AppException extends Exception {
  HttpStatus status;
  String message;
  List<String> errors = new ArrayList<>();

  public AppException(HttpStatus status, String message) {
    super(message);
    this.message = message;
    this.status = status;
  }

  public AppException(String message) {
    super(message);
    this.message = message;
    this.status = HttpStatus.BAD_REQUEST;
  }

  public AppException(List<String> errors) {
    this.errors = errors;
    this.status = HttpStatus.BAD_REQUEST;
  }
}
