package com.yoel.fernandez.ApiAttendance.Controller;

import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoel.fernandez.ApiAttendance.DTO.EmployedDTO;
import com.yoel.fernandez.ApiAttendance.Entity.Employed;
import com.yoel.fernandez.ApiAttendance.Entity.SecurityUser;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    


    @GetMapping("/login")
    public ResponseEntity<EmployedDTO> login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.getPrincipal() instanceof SecurityUser) {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            EmployedDTO employedDTO = securityUser.getEmployed(); // Agrega un getter en SecurityUser
            return ResponseEntity.ok(employedDTO);
         }

        return ResponseEntity.status(401).build();
    }

   
}
