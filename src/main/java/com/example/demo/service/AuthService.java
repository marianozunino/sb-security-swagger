package com.example.demo.service;

import com.example.demo.dto.auth.response.TokenDto;
import com.example.demo.exception.ApiException;
import com.example.demo.repository.PersonRepository;
import com.example.demo.util.JwtUtil;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final PersonRepository personRepository;
  private final JwtUtil jwtUtil;

  public AuthService(PersonRepository personRepository, JwtUtil jwtUtil) {
    this.personRepository = personRepository;
    this.jwtUtil = jwtUtil;
  }

  private String verifyFirebaseToken() {
    return "Greg"; //Todo: return user email from firebase token
  }

  public TokenDto login(String firebaseToken) throws ApiException {
    var name = verifyFirebaseToken();
    var person =
      this.personRepository.findByName(name)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found"));
    //Al tirar 404 el frontend ya sabe que este usuario NO existe aun en nuestra base, asi que comienza el proceso de crear el usuario
    //El front ahora le va a pegar a otro endpoint para crear una cuenta donde TAMBIEN se debe recibir el token de firebase
    var claims = new HashMap<String, Object>();
    claims.put("extraData", person.getId());
    return new TokenDto(this.jwtUtil.createToken(claims, person.getName()));
  }
}
