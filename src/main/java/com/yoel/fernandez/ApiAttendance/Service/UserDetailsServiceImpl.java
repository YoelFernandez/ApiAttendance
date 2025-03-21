package com.yoel.fernandez.ApiAttendance.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yoel.fernandez.ApiAttendance.DTO.EmployedDTO;
import com.yoel.fernandez.ApiAttendance.Entity.Employed;
import com.yoel.fernandez.ApiAttendance.Entity.SecurityUser;
import com.yoel.fernandez.ApiAttendance.Repositoy.EmployedRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 
public class UserDetailsServiceImpl implements UserDetailsService{
    
    private EmployedRepository employedRepository;
    private EmployedService employedService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        EmployedDTO employedDTO = employedService.convertToDTO(employedRepository.findByCorreoEmpleado(username));
        
        if(employedDTO == null){
            
            throw new UsernameNotFoundException("User not found");
        }
        return new SecurityUser(employedDTO);
    }

}
