package com.example.demo.controller;

import com.example.demo.dto.ApiErrorResponse;
import com.example.demo.dto.auth.response.TokenDto;
import com.example.demo.exception.ApiException;
import com.example.demo.service.AuthService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
class AuthController {

  private final AuthService authService;

  AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/auth")
  @ApiResponses(
    value = {
      @ApiResponse(
        responseCode = "404",
        content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
        }
      ),
      @ApiResponse(
        responseCode = "401",
        content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
        }
      )
    }
  )
  ResponseEntity<TokenDto> auth(@RequestHeader("x-firebase-token") String fbToken) throws ApiException {
    if (fbToken != null && fbToken.toLowerCase().startsWith("bearer ")) {
      var jwt = fbToken.substring(7);
      var authResponse = this.authService.login(jwt);
      return ResponseEntity.ok(authResponse);
    }
    throw new ApiException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
  }
}
