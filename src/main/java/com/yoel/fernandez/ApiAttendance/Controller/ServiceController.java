package com.yoel.fernandez.ApiAttendance.Controller;

import java.util.List;

import org.springframework.context.annotation.Lazy;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoel.fernandez.ApiAttendance.DTO.ServiceEntityDTO;
import com.yoel.fernandez.ApiAttendance.Entity.ServiceEntity;
import com.yoel.fernandez.ApiAttendance.Service.ServiceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/service")
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class ServiceController {

    private final ServiceService serviceService;


    
    @PostMapping("/nuevo")
    //@PreAuthorize("HasAuthority('ADMIN')")
    private void crearServicio(@RequestBody ServiceEntity service){
        serviceService.crearServicio(service);
    }


    @GetMapping("/listar")
    private List<ServiceEntity> listarServicios(){
        return serviceService.listarServicios();
    }

    
    @GetMapping("/listarDTO")
    private List<ServiceEntityDTO> listarServiciosDTO(){
        return serviceService.listarServiciosDTO();
    }

}
