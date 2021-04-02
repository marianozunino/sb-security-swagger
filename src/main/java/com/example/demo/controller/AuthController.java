package com.example.demo.controller;

import com.example.demo.dto.auth.request.AuthRequest;
import com.example.demo.dto.auth.response.AuthResponse;
import com.example.demo.service.MyUserDetailsService;
import com.example.demo.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class AuthController {

  private final AuthenticationManager authenticationManager;
  private final MyUserDetailsService userDetailsService;
  private final JwtUtil jwtUtil;

  public AuthController(AuthenticationManager authenticationManager, MyUserDetailsService userDetailsService, JwtUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping("/auth")
  ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest authRequest) throws Exception {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
    } catch (BadCredentialsException e) {
      throw new Exception("Invalid user or password");
    }
    final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
    final String jwt = jwtUtil.generateToken(userDetails);
    return ResponseEntity.ok(new AuthResponse(jwt));
  }
}
