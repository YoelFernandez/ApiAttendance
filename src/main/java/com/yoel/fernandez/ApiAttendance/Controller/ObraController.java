package com.yoel.fernandez.ApiAttendance.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.yoel.fernandez.ApiAttendance.DTO.ObraDTO;
import com.yoel.fernandez.ApiAttendance.Service.ObraService;
import com.yoel.fernandez.ApiAttendance.Service.UploadFileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/obra")
@RequiredArgsConstructor(onConstructor_ = {@Lazy}) // Forzamos carga diferida
public class ObraController {

    private final ObraService obraService;
    private final UploadFileService uploadFileService; 


    @PostMapping(value= "/nuevoDTO", consumes = "multipart/form-data")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ObraDTO createObra(@RequestParam("file") MultipartFile file, @RequestPart("obraDTO") ObraDTO obraDTO){
        try {
            String imageUrl = uploadFileService.uploadFile(file);
            return obraService.createObra(obraDTO, imageUrl);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al subir el archivo", e);
        }
       
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
