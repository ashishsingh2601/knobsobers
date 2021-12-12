package com.springboot.boilerplate.service;

import com.springboot.boilerplate.dto.AddRecordDto;
import com.springboot.boilerplate.entity.Sample;

import java.util.List;

public interface SampleService {

  List<Sample> getRecords(int page, int limit) throws Exception;

  Sample getRecord(Long id) throws Exception;

  String addRecord(AddRecordDto addRecordDto) throws Exception;

  String updateRecord(Long id, AddRecordDto addRecordDto) throws Exception;

}
