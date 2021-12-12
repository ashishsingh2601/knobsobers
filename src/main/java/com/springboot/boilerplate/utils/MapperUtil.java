package com.springboot.boilerplate.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MapperUtil {

  public ObjectMapper objectMapper;

  @Autowired
  public MapperUtil(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public <T> T jsonParse(String jsonString, Class<T> clazz) {
    try {
      return objectMapper.readValue(jsonString, clazz);
    } catch (JsonProcessingException e) {
      log.error("Error in parsing String to json : {} , Error : {}", jsonString, e.getMessage());
    }
    return null;
  }

  public String jsonStringify(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      log.error("Error in converting json : {} to String, Error: {}", object.toString(), e.getMessage());
    }
    return "";
  }

}
