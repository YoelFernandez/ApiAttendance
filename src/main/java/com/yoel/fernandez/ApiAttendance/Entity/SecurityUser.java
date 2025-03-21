package com.yoel.fernandez.ApiAttendance.Entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.yoel.fernandez.ApiAttendance.DTO.EmployedDTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class SecurityUser implements UserDetails{
    
    private EmployedDTO employedDTO;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(employedDTO.getRole().name()));
    }

   

    @Override
    public String getPassword() {
        return employedDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return employedDTO.getCorreoEmpleado();
    }

    public EmployedDTO getEmployed() {
        return employedDTO;
    }


}
