package com.example.demo.util;

import com.example.demo.exception.ApiException;
import com.example.demo.model.UserContext;
import io.jsonwebtoken.*;
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {

  private final String SECRET_KEY;

  @Resource(name = "requestUserDataContext")
  private UserContext userContext;

  public JwtUtil(@Value("${jwt.secret:DEFAULT_KEY_VALUE}") String secret_key) {
    SECRET_KEY = secret_key;
  }

  public String createToken(Map<String, Object> claims, String subject) {
    return Jwts
      .builder()
      .setClaims((claims))
      .setSubject(subject)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 5)) //TODO: 5 Horas
      .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
      .compact();
  }

  public void setAuthContext(String token) throws ApiException {
    try {
      var claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
      var userName = claims.getSubject();
      this.userContext.setUserName(userName);
    } catch (SignatureException | ExpiredJwtException | MalformedJwtException e) {
      throw new ApiException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }
  }
}
