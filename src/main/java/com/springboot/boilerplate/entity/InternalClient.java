package com.springboot.boilerplate.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "internal_client")
public class InternalClient extends BaseEntity {
  @Column(name = "secret")
  private String secret;

  @Column(name = "client_name")
  private String clientName;

  @Column(name = "status")
  private String status;
}
