package com.yoel.fernandez.ApiAttendance.Service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;

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
    //     connectionString = System.getenv("AZURE_STORAGE_CONNECTION_STRING");
    //     containerName = System.getenv("STORAGE_CONTAINER_NAME");

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
}
