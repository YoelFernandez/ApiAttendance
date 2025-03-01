package com.yoel.fernandez.ApiAttendance.Controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoel.fernandez.ApiAttendance.DTO.EmployedDTO;
import com.yoel.fernandez.ApiAttendance.Entity.Employed;
import com.yoel.fernandez.ApiAttendance.Service.EmployedService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/employed")
@RequiredArgsConstructor
public class EmployedController {

    private final EmployedService employedService;

    @PostMapping("/nuevo")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public void crearEmpleado(@RequestBody Employed employed) {
        employedService.crearEmpleado(employed);
    }

    @PostMapping("/nuevoDTO")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void crearEmpleadoDTO(@RequestBody EmployedDTO employedDTO) {
        employedService.createEmployeDTO(employedDTO);
    }

    @GetMapping("/listar")
    public List<Employed> listarEmpleado() {
        return employedService.listarEmpleados();
    }


    @GetMapping("/listarDTO")
    public List<EmployedDTO> listarEmpleadosDTO(){
        return employedService.getAllEmployees();
    }

    @DeleteMapping("/eliminar/{codigo}")
    public void eliminarEmpleado(@PathVariable String codigo){
        employedService.eliminarEmpleado(codigo);
    }

    @PutMapping("/actualizar/{codigo}")
    public EmployedDTO actualizarEmpleadoDTO(@PathVariable String codigo, @RequestBody  EmployedDTO employedDTO){
        return employedService.actualEmployedDTO(codigo, employedDTO);
    }

    //Luego esto se borra porque es solo para probar si funciona
    @GetMapping("/probandoa")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String funciona() {
        return "siii administrador";
    }

    @GetMapping("/probandoc")
    public String funciona2() {
        return "siii cliente";
    }
}
