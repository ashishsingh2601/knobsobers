package com.springboot.boilerplate.interservice.merchant;

import com.springboot.boilerplate.constant.Constant;
import com.springboot.boilerplate.interservice.merchant.dto.APIResponseDto;
import com.springboot.boilerplate.interservice.merchant.dto.BasicMerchantDetailsDto;
import com.springboot.boilerplate.interservice.merchant.dto.MerchantDetailsDto;
import com.springboot.boilerplate.utils.HmacCalculator;
import com.springboot.boilerplate.utils.MapperUtil;
import com.springboot.boilerplate.utils.RestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MerchantServiceImpl implements MerchantService {

  @Value("${merchant.service.host:#{''}}")
  private String MERCHANT_HOST;

  private final MapperUtil mapperUtil;
  private final RestUtil restUtil;
  private final HmacCalculator hmacCalculator;

  @Autowired
  public MerchantServiceImpl(
      MapperUtil mapperUtil,
      RestUtil restUtil,
      HmacCalculator hmacCalculator
  ) {
    this.mapperUtil = mapperUtil;
    this.restUtil = restUtil;
    this.hmacCalculator = hmacCalculator;
  }

  public BasicMerchantDetailsDto fetchMerchantDetails(String token) throws Exception {
    Map<String, String> headers = new HashMap<>();
    headers.put("token", token);
    headers.put("Client-Name", Constant.INTERNAL_CLIENT_NAME);
    String url = MERCHANT_HOST + "/merchant/v1/verifytoken";

    APIResponseDto responseDto = restUtil.getForObject(url, headers, APIResponseDto.class);
    BasicMerchantDetailsDto merchantDto = mapperUtil.jsonParse(
        mapperUtil.jsonStringify(responseDto.getData()),
        BasicMerchantDetailsDto.class
    );
    log.info("Merchant Details from token: {}", mapperUtil.jsonStringify(merchantDto));
    return merchantDto;
  }

  public MerchantDetailsDto fetchMerchantDetails(Long merchantId, List<String> scopeList) throws Exception {
    String url = MERCHANT_HOST + "/merchant/v1/internalclient/merchantinfo";

    Map<String, String> queryParams = new HashMap<>();
    queryParams.put("merchantids", String.valueOf(merchantId));
    queryParams.put("scopes", String.join(",", scopeList));

    Map<String, String> header = new HashMap<>();
    header.put(
        "hash",
        hmacCalculator.hmacByPayloadAndClient(queryParams, Constant.INTERNAL_CLIENT_NAME)
    );
    header.put("Client-Name", Constant.INTERNAL_CLIENT_NAME);

    APIResponseDto responseDto = restUtil.getForObject(url, header, queryParams, APIResponseDto.class);
    List<MerchantDetailsDto> merchantDetailsDtos =
        mapperUtil.objectMapper.readValue(mapperUtil.jsonStringify(responseDto.getData()),
        mapperUtil.objectMapper.getTypeFactory().constructCollectionType(List.class, MerchantDetailsDto.class));

    log.info("Merchant Details: {}", mapperUtil.jsonStringify(merchantDetailsDtos));
    return merchantDetailsDtos.get(0);
  }

}
