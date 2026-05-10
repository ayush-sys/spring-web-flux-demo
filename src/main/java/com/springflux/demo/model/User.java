package com.springflux.demo.model;

import lombok.Data;

@Data
public class User {
  private String id;
  private String name;
  private String timestamp;

  public User(String id, String name, String timestamp) {
    this.id = id;
    this.name = name;
    this.timestamp = timestamp;
  }
}
