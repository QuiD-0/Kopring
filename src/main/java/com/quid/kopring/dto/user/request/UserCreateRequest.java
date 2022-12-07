package com.quid.kopring.dto.user.request;

import lombok.Builder;

public class UserCreateRequest {

  private String name;
  private Integer age;

  public String getName() {
    return name;
  }

  public Integer getAge() {
    return age;
  }

  @Builder
  public UserCreateRequest(String name, Integer age) {
    this.name = name;
    this.age = age;
  }
}
