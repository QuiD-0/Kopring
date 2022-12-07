package com.quid.kopring.dto.user.request;

import lombok.Builder;

public class UserUpdateRequest {

  private long id;
  private String name;

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Builder
  public UserUpdateRequest(long id, String name) {
    this.id = id;
    this.name = name;
  }
}
