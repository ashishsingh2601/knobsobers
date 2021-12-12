package com.springboot.boilerplate.dto;

import lombok.Data;
import org.springframework.util.ObjectUtils;

@Data
public class AddRecordDto {
  String address;
  Long mobile;
  Boolean status;

  public String validate() {
    if (ObjectUtils.isEmpty(address)) {
      return "Address can't be empty!";
    }
    if (ObjectUtils.isEmpty(mobile)) {
      return "Mobile can't be empty!";
    }
    if (ObjectUtils.isEmpty(status)) {
      return "Status can't be empty!";
    }
    return null;
  }
}
