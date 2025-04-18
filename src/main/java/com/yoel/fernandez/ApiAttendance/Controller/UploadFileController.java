package com.yoel.fernandez.ApiAttendance.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yoel.fernandez.ApiAttendance.Service.UploadFileService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/storage")
@RequiredArgsConstructor
public class UploadFileController {

    private final UploadFileService uploadFileService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = uploadFileService.uploadFile(file);
            return ResponseEntity.ok(Map.of("url", fileUrl));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error al subir la imagen: " + e.getMessage()));
        }
    }

    @GetMapping("/listar")
    public List<String> listarImagenes(){
        return uploadFileService.listarURL();
    }

    @GetMapping("/por-fecha")
    public ResponseEntity<List<String>> obtenerImagenesPorFecha(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<String> imagenes = uploadFileService.listarPorFecha(fecha);
        return ResponseEntity.ok(imagenes);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarImagen(@RequestParam("nombre") String nombreArchivo) {
        boolean eliminada = uploadFileService.eliminarImagen(nombreArchivo);
    
        if (eliminada) {
            return ResponseEntity.ok("Imagen eliminada correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la imagen.");
        }
    }
    

}
