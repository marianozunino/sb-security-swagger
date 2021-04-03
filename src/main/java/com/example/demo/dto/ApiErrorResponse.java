package com.example.demo.dto;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApiErrorResponse {

  private final int statusCode;
  private final String status;
  private final String error;
  private List<ApiValidationError> subErrors;

  public ApiErrorResponse(HttpStatus status, String error) {
    this.statusCode = status.value();
    this.status = status.getReasonPhrase();
    this.error = error;
  }

  public ApiErrorResponse(HttpStatus status, String error, List<ApiValidationError> apiValidationErrors) {
    this(status, error);
    this.subErrors = apiValidationErrors;
  }
}
