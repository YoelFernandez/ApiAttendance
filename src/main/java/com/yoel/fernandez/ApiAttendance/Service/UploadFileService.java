package com.yoel.fernandez.ApiAttendance.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.models.BlobItem;

import jakarta.annotation.PostConstruct;

@Service
public class UploadFileService {

    private BlobContainerClient containerClient;
    

    @Value("${AZURE_STORAGE_CONNECTION_STRING}")
    private String connectionString;

    @Value("${STORAGE_CONTAINER_NAME}")
    private String containerName;

    @PostConstruct
    public void init() {

        if (connectionString == null || connectionString.isEmpty()) {
            throw new RuntimeException("Error: AZURE_STORAGE_CONNECTION_STRING no está definida.");
        }
        if (containerName == null || containerName.isEmpty()) {
            throw new RuntimeException("Error: STORAGE_CONTAINER_NAME no está definido.");
        }

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        this.containerClient = blobServiceClient.getBlobContainerClient(containerName);
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        
        // Configurar opciones de carga con Content-Type
        BlobHttpHeaders headers = new BlobHttpHeaders()
                .setContentType(file.getContentType());  

        // Subir el archivo con los headers
        blobClient.upload(file.getInputStream(), file.getSize(), true);
        blobClient.setHttpHeaders(headers); 

        return blobClient.getBlobUrl();
    }

    public List<String>  listarURL(){
        List<String> urls = new ArrayList<>();
    
        // Iterar sobre los blobs en el contenedor
        for (BlobItem blobItem : containerClient.listBlobs()) {
            String url = containerClient.getBlobClient(blobItem.getName()).getBlobUrl();
            urls.add(url);
        }
        
        return urls;
    }

    public List<String> listarPorFecha(LocalDate fechaDeseada) {
        List<String> urlsFiltradas = new ArrayList<>();

        for (BlobItem blobItem : containerClient.listBlobs()) {
            BlobClient blobClient = containerClient.getBlobClient(blobItem.getName());
            OffsetDateTime lastModified = blobClient.getProperties().getLastModified();

            // Convertir OffsetDateTime a LocalDate
            LocalDate fechaBlob = lastModified.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // Comparar con la fecha deseada
            if (fechaBlob.equals(fechaDeseada)) {
                urlsFiltradas.add(blobClient.getBlobUrl());
            }
        }

        return urlsFiltradas;
    }


    public boolean eliminarImagen(String nombreArchivo) {
        BlobClient blobClient = containerClient.getBlobClient(nombreArchivo);
    
        if (blobClient.exists()) {
            blobClient.delete();
            return true;
        } else {
            return false;
        }
    }

    
    

}
