package com.example.demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApiException extends Exception {

  private HttpStatus status;
  private String error;

  public ApiException(HttpStatus status, String error) {
    this.status = status;
    this.error = error;
  }
}
