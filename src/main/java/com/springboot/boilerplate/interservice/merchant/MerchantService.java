package com.springboot.boilerplate.interservice.merchant;

import com.springboot.boilerplate.interservice.merchant.dto.BasicMerchantDetailsDto;
import com.springboot.boilerplate.interservice.merchant.dto.MerchantDetailsDto;

import java.util.List;

public interface MerchantService {

  BasicMerchantDetailsDto fetchMerchantDetails(String token) throws Exception;

  MerchantDetailsDto fetchMerchantDetails(Long merchantId, List<String> scopeList) throws Exception;

}
