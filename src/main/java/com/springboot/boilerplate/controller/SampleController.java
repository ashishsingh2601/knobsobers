package com.springboot.boilerplate.controller;

import com.springboot.boilerplate.constant.Constant;
import com.springboot.boilerplate.dto.AddRecordDto;
import com.springboot.boilerplate.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Constant.BASE_URL_V1 + "/sample")
public class SampleController {

  @Autowired
  SampleService sampleService;

  @GetMapping
  public ResponseEntity getRecords(
      @RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE) int page,
      @RequestParam(required = false, defaultValue = Constant.DEFAULT_LIMIT) int limit
  ) throws Exception {
    return ResponseEntity.ok(sampleService.getRecords(page, limit));
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity getRecord(
      @PathVariable Long id
  ) throws Exception {
    return ResponseEntity.ok(sampleService.getRecord(id));
  }

  @PostMapping
  public ResponseEntity addRecord(
      @RequestBody AddRecordDto addRecordDto
  ) throws Exception {
    return ResponseEntity.ok(sampleService.addRecord(addRecordDto));
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity updateRecord(
      @PathVariable Long id,
      @RequestBody AddRecordDto addRecordDto
  ) throws Exception {
    return ResponseEntity.ok(sampleService.updateRecord(id, addRecordDto));
  }
}
