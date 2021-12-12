package com.springboot.boilerplate.interservice.merchant.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MerchantDetailsDto {

  BasicMerchantDetailsDto merchantDetail;
  MerchantBankDetailsDto bankDetail;
  List<MerchantVPADetailsDto> vpaDetail;
  List<MerchantAddressDto> addressDetail;
  MerchantUserDto merchantUser;

}
