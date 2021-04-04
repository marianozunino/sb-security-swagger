package com.example.demo.filter;

import com.example.demo.util.JwtUtil;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;

  public JwtRequestFilter(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @SneakyThrows
  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    @NotNull HttpServletResponse response,
    @NotNull FilterChain filterChain
  ) {
    var bearer = request.getHeader("Authorization");
    if (bearer != null && bearer.toLowerCase().startsWith("bearer ")) {
      var jwt = bearer.substring(7);
      this.jwtUtil.setAuthContext(jwt);
    }
    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    return (
      request.getRequestURI().startsWith("/auth") ||
      request.getRequestURI().startsWith("/api-docs") ||
      request.getRequestURI().startsWith("/swagger")
    );
  }
}
