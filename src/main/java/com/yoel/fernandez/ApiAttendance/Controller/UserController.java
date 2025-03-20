package com.yoel.fernandez.ApiAttendance.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoel.fernandez.ApiAttendance.DTO.EmployedDTO;

@RestController
@RequestMapping("/user")
public class UserController {
//  @GetMapping("/me")
//     public ResponseEntity<?> getUserDetails(Authentication authentication) {
//         EmployedDTO user = (EmployedDTO) authentication.getPrincipal();
//         Map<String, String> response = new HashMap<>();
//         response.put("username", user.getCorreoEmpleado());
//         response.put("role", user.getAuthorities().iterator().next().getAuthority()); // Obtiene el rol
//         return ResponseEntity.ok(response);
//     }
}

