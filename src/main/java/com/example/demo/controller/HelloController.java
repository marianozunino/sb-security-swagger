package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.CarDto;
import com.example.demo.mapper.CarMapper;
import com.example.demo.model.Car;
import com.example.demo.model.CarType;
import com.example.demo.service.MyUserDetailsService;
import com.example.demo.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController {

  private final AuthenticationManager authenticationManager;
  private final MyUserDetailsService userDetailsService;
  private final JwtUtil jwtUtil;

  public HelloController(AuthenticationManager authenticationManager, MyUserDetailsService userDetailsService, JwtUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.jwtUtil = jwtUtil;
  }

  @GetMapping("/hello")
  @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
  ResponseEntity<CarDto> hello() {
    Car car = new Car("Morris", 5, CarType.SEDAN);
    CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);

    return ResponseEntity.ok(carDto);
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
