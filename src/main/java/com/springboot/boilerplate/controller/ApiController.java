package com.springboot.boilerplate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

  @GetMapping
  public ResponseEntity healthCheck() {
    return ResponseEntity.ok("App is running");
  }

}
