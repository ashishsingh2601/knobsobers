package com.springboot.boilerplate.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "master_key")
public class MasterKey extends BaseEntity {
  @Column(name = "secret")
  private String secret;

  @Column(name = "description")
  private String description;

  @Column(name = "active")
  private boolean active;
}
