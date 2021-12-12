package com.springboot.boilerplate.service.Impl;

import com.springboot.boilerplate.dao.SampleDao;
import com.springboot.boilerplate.dto.AddRecordDto;
import com.springboot.boilerplate.entity.Sample;
import com.springboot.boilerplate.exception.AppException;
import com.springboot.boilerplate.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SampleServiceImpl implements SampleService {

  @Autowired
  SampleDao sampleDao;

  public String addRecord(AddRecordDto addRecordDto) throws Exception {
    if (!ObjectUtils.isEmpty(addRecordDto.validate())) {
      throw new AppException(addRecordDto.validate());
    }
    try {
      Sample sample = new Sample();
      sample.setAddress(addRecordDto.getAddress());
      sample.setMobile(addRecordDto.getMobile());
      sample.setStatus(addRecordDto.getStatus());
      sampleDao.save(sample);
      return "Record Saved Successfully!";
    } catch (Exception e) {
      log.error("Add Record exception: {}", e.getMessage());
      throw new AppException("Failed to add record!");
    }
  }

  public String updateRecord(Long id, AddRecordDto addRecordDto) throws Exception {
    if (!ObjectUtils.isEmpty(addRecordDto.validate())) {
      throw new AppException(addRecordDto.validate());
    }
    Optional<Sample> sampleObj = sampleDao.findById(id);
    if (sampleObj.isEmpty()) {
      throw new AppException("Record Not found!");
    }
    try {
      Sample sample = sampleObj.get();
      sample.setAddress(addRecordDto.getAddress());
      sample.setMobile(addRecordDto.getMobile());
      sample.setStatus(addRecordDto.getStatus());
      sampleDao.save(sample);
      return "Record Updated Successfully!";
    } catch (Exception e) {
      log.error("Update Record exception: {}", e.getMessage());
      throw new AppException("Failed to update record!");
    }
  }

  public List<Sample> getRecords(int page, int limit) throws Exception {
    Pageable pageable = PageRequest.of(page - 1, limit);
    try {
      return sampleDao.getRecords(pageable);
    } catch (Exception e) {
      log.error("Get Sample records exception: {}", e.getMessage());
      throw new AppException("Failed to get records!");
    }
  }

  public Sample getRecord(Long id) throws Exception {
    Optional<Sample> sampleOptional = sampleDao.findById(id);
    if (sampleOptional.isEmpty()){
      throw new AppException("Record not found!");
    }
    return sampleOptional.get();
  }
}
