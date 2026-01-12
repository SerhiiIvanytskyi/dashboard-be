package com.example.dashboard2026be.config;

import com.example.dashboard2026be.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtService jwtService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
      filterChain.doFilter(request, response);
      return;
    }

    String path = request.getServletPath();
    String method = request.getMethod();

    if ((path.startsWith("/articles") || path.startsWith("/article")) && method.equals("GET")) {
      filterChain.doFilter(request, response);
      return;
    }

    if (path.startsWith("/auth")) {
      filterChain.doFilter(request, response);
      return;
    }

    String authHeader = request.getHeader("Authorization");
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }

    String token = authHeader.substring(7);

    if (!jwtService.isTokenValid(token)) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }

    String email = jwtService.extractUsername(token);

    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(email, null, List.of());

    SecurityContextHolder.getContext().setAuthentication(authentication);
    filterChain.doFilter(request, response);
  }
}
