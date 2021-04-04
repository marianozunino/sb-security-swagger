package com.example.demo.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException extends Exception {

  private static final long serialVersionUID = 1L;
  private HttpStatus status;
  private String error;

  public ApiException(HttpStatus status, String error) {
    this.status = status;
    this.error = error;
  }
}
