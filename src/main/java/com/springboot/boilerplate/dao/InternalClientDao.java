package com.springboot.boilerplate.dao;

import com.springboot.boilerplate.entity.InternalClient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InternalClientDao extends CrudRepository<InternalClient, Long>  {
  Optional<InternalClient> findByClientName(String clientName);
}
