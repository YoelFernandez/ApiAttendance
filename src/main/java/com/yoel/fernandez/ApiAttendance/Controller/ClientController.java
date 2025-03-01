package com.yoel.fernandez.ApiAttendance.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoel.fernandez.ApiAttendance.DTO.ClientDTO;
import com.yoel.fernandez.ApiAttendance.Entity.Client;
import com.yoel.fernandez.ApiAttendance.Service.ClientService;

import lombok.RequiredArgsConstructor;
import java.util.List;


@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
     

    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void nuevoClient(@RequestBody Client client){
        clientService.crearCliente(client);
    }

    @PostMapping("/nuevoDTO")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> nuevoClientDTO(@RequestBody ClientDTO clientDTO){
        clientService.crearClienteDTO(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Client creado con éxito");

    }


    @GetMapping("/listarDTO")
    public List<ClientDTO> listarClientes(){
        return clientService.listarCliente();
    }

    @GetMapping("/probandoo")
    private String funciona(){
        return "siis";
    }

}
