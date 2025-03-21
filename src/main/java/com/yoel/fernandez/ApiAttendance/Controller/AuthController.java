package com.yoel.fernandez.ApiAttendance.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yoel.fernandez.ApiAttendance.Config.JwtUtil;
import com.yoel.fernandez.ApiAttendance.DTO.EmployedDTO;
import com.yoel.fernandez.ApiAttendance.Entity.SecurityUser;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String correo, @RequestParam String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(correo, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        
        // Generar token
        String token = jwtUtil.generateToken(securityUser.getUsername());

        return ResponseEntity.ok(new LoginResponse(token, securityUser.getEmployed()));
    }
}

// Clase para la respuesta de login
class LoginResponse {
    public String token;
    public EmployedDTO user;

    public LoginResponse(String token, EmployedDTO user) {
        this.token = token;
        this.user = user;
    }
}
