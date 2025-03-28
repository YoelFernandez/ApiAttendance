package com.yoel.fernandez.ApiAttendance.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.yoel.fernandez.ApiAttendance.DTO.EmployedDTO;
import com.yoel.fernandez.ApiAttendance.Service.EmployedService;
import com.yoel.fernandez.ApiAttendance.Service.UploadFileService;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employed")
@RequiredArgsConstructor
public class EmployedController {

    private final EmployedService employedService;
    private final UploadFileService uploadFileService;

    
    @PostMapping("/nuevoDTO")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void crearEmpleadoDTO(@RequestBody EmployedDTO employedDTO) {
        employedService.createEmployeDTO(employedDTO);
    }

    @PostMapping(value = "/nuevoDTOImage", consumes = "multipart/form-data")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> crearEmpleadoDTOWhitImage(@RequestParam("file") MultipartFile file, @RequestPart("employedDTO") EmployedDTO employedDTO) {
        try {
            String imageUrl = uploadFileService.uploadFile(file);
            employedService.createEmployeDTOWhithImage(employedDTO,imageUrl);
            return ResponseEntity.ok("Empleado creado exitosamente.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir la imagen: " + e.getMessage());
        }
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



    @GetMapping("/{codigo}")
    public EmployedDTO retornarPorID(@PathVariable String codigo){
        return employedService.retornarPorId(codigo);
    }


    //Luego esto se borra porque es solo para probar si funciona
    @GetMapping("/admi")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String funciona() {
        return "siii administrador";
    }

    @GetMapping("/probandoc")
    public String funciona2() {
        return "siii cliente";
    }



}
