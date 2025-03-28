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
import org.springframework.web.bind.annotation.RestController;

import com.yoel.fernandez.ApiAttendance.DTO.ClientDTO;
import com.yoel.fernandez.ApiAttendance.Service.ClientService;

import lombok.RequiredArgsConstructor;
import java.util.List;


@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
     

    

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


    @DeleteMapping("/eliminar/{codigo}")
    public void eliminarCliente(@PathVariable String codigo){
        clientService.eliminarCliente(codigo);
    }

    @PutMapping("/actualizar/{codigo}")
    public ClientDTO actualizarEmpleadoDTO(@PathVariable String codigo, @RequestBody  ClientDTO clientDTO){
        return clientService.actualClienteDTO(codigo, clientDTO);
    }



    @GetMapping("/{codigo}")
    public ClientDTO retornarPorID(@PathVariable String codigo){
        return clientService.retornarPorId(codigo);
    }

}
