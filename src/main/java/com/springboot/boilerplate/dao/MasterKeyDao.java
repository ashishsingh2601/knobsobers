package com.springboot.boilerplate.dao;

import com.springboot.boilerplate.entity.MasterKey;
import org.springframework.data.repository.CrudRepository;

public interface MasterKeyDao extends CrudRepository<MasterKey, Long> {

  MasterKey findByActiveIsTrue();
}