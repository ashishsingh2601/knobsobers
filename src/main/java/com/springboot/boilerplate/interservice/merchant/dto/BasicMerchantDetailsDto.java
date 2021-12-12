package com.springboot.boilerplate.interservice.merchant.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BasicMerchantDetailsDto {

  String name;
  String bussinessName;
  String bussinessCategory;
  String merchantType;
  String mobile;
  String city;
  String state;
  String address;
  Integer zipCode;
  Double latitude;
  Double longitude;
  String panNumber;
  String gstnNo;
  String shopType;
  String beneficiaryName;
  String subCategory;
  String companyType;
  Long id;
  String mid;
  Long merchantUserID;

}
