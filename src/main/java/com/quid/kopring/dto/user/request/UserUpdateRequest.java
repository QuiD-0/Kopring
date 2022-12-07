package com.quid.kopring.dto.user.request;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserUpdateRequest {

  private long id;
  private String name;

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

}
