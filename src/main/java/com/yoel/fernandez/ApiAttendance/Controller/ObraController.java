package com.yoel.fernandez.ApiAttendance.Controller;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoel.fernandez.ApiAttendance.DTO.ObraDTO;
import com.yoel.fernandez.ApiAttendance.Entity.Obra;
import com.yoel.fernandez.ApiAttendance.Service.ObraService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/obra")
@RequiredArgsConstructor(onConstructor_ = {@Lazy}) // Forzamos carga diferida
public class ObraController {

    private final ObraService obraService; 

    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void crearObra(@RequestBody Obra obra){
        obraService.crearObra(obra);
    }


    @PostMapping("/nuevoDTO")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ObraDTO createObra(@RequestBody ObraDTO obraDTO){
        return obraService.createObra(obraDTO);
    }

    @GetMapping("/listarDTO")
    public List<ObraDTO> listarObrasDTO(){
        return obraService.listarObrasDTO();
    }



    @DeleteMapping("/eliminar/{codigo}")
    public void eliminarEmpleado(@PathVariable String codigo){
        obraService.eliminarObra(codigo);
    }

    @PutMapping("/actualizar/{codigo}")
    public ObraDTO actualizarEmpleadoDTO(@PathVariable String codigo, @RequestBody  ObraDTO obraDTO){
        return obraService.actualObraDTO(codigo, obraDTO);
    }



    @GetMapping("/{codigo}")
    public ObraDTO retornarPorID(@PathVariable String codigo){
        return obraService.retornarPorId(codigo);
    }



}
