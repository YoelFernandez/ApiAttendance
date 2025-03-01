package com.yoel.fernandez.ApiAttendance.Entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class SecurityUser implements UserDetails{
    
    private Employed employed;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(employed.getRole().name()));
    }

   

    @Override
    public String getPassword() {
        return employed.getPassword();
    }

    @Override
    public String getUsername() {
        return employed.getCorreoEmpleado();
    }


}
