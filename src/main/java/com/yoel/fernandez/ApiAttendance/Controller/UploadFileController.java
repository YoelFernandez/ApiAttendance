package com.yoel.fernandez.ApiAttendance.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

}
