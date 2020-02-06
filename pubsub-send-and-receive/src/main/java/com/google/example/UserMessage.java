package com.google.example;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;


public class UserMessage {

  private String body;

  private String value;

  private LocalDateTime createdAt;


  public UserMessage(String body, @Header("key") String value) {
    this.body = body;
    this.value = value;
    this.createdAt = LocalDateTime.now();
  }

  public String getBody() {
    return this.body;
  }

  public String getValue() {
    return this.value;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

}