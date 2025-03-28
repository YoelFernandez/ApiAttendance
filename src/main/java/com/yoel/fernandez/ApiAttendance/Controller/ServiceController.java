package com.yoel.fernandez.ApiAttendance.Controller;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoel.fernandez.ApiAttendance.DTO.ServiceEntityDTO;
import com.yoel.fernandez.ApiAttendance.Service.ServiceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/service")
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class ServiceController {

    private final ServiceService serviceService;



    @PostMapping("/nuevoDTO")
    private ServiceEntityDTO nuevoServicio(@RequestBody ServiceEntityDTO serviceEntityDTO){
        return serviceService.nuevoServicio(serviceEntityDTO);

    }

    
    @GetMapping("/listarDTO")
    private List<ServiceEntityDTO> listarServiciosDTO(){
        return serviceService.listarServiciosDTO();
    }


    
    @DeleteMapping("/eliminar/{codigo}")
    public void eliminarEmpleado(@PathVariable String codigo){
        serviceService.eliminarServicio(codigo);
    }

    @PutMapping("/actualizar/{codigo}")
    public ServiceEntityDTO actualizarEmpleadoDTO(@PathVariable String codigo, @RequestBody  ServiceEntityDTO serviceEntityDTO){
        return serviceService.actualizarServicioDTO(codigo, serviceEntityDTO);
    }



    @GetMapping("/{codigo}")
    public ServiceEntityDTO retornarPorID(@PathVariable String codigo){
        return serviceService.retornarPorId(codigo);
    }

}
