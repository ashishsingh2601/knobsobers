package com.springboot.boilerplate.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "boilerplate_sample")
public class Sample extends BaseEntity {

  @Column(name = "address")
  String address;

  @Column(name = "mobile")
  Long mobile;

  @Column(name = "status")
  Boolean status;
}
