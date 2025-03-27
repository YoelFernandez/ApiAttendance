package com.yoel.fernandez.ApiAttendance.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yoel.fernandez.ApiAttendance.DTO.ClientDTO;
import com.yoel.fernandez.ApiAttendance.Entity.Client;
import com.yoel.fernandez.ApiAttendance.Repositoy.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public void crearCliente(Client client){
        clientRepository.save(client);
    }

    public List<ClientDTO> listarCliente(){
        List<Client> listaClientes = clientRepository.findAll();
        return listaClientes.stream().map(this::convertirDTO).collect(Collectors.toList());
    }


    public ClientDTO convertirDTO(Client client){
        return new ClientDTO(
            client.getCodCliente(),
            client.getNombreCliente(),
            client.getCorreoCliente(),
            client.getTelefonoCliente()
        );
    }

    public void crearClienteDTO(ClientDTO clientDTO){
        Client client  = new Client();
            //client.setCodCliente(clientDTO.getCodCliente());
            client.setNombreCliente(clientDTO.getNombreCliente());
            client.setTelefonoCliente(clientDTO.getTelefonoCliente());
            client.setCorreoCliente(clientDTO.getCorreoCliente());
            clientRepository.save(client);
            
    }

    public void eliminarCliente(String codigo) {
        if(clientRepository.existsById(codigo)){
            clientRepository.deleteById(codigo);
        }else{
            throw new UnsupportedOperationException("Cliente no encontrado: codigo --> " + codigo);
        }
    }

    public ClientDTO actualClienteDTO(String codigo, ClientDTO clientDTO) {
        return clientRepository.findById(codigo).map(client->{
            client.setCorreoCliente(clientDTO.getCorreoCliente());
            //client.setFechaCreacion(clientDTO.get());
            client.setNombreCliente(clientDTO.getNombreCliente());
            client.setTelefonoCliente(clientDTO.getTelefonoCliente());
            Client clientReturn = clientRepository.save(client);
            return convertirDTO(clientReturn);

        }).orElseThrow(()-> new RuntimeException("Cliente no encontrado: codigo --> " + codigo));
    }

    public ClientDTO retornarPorId(String codigo) {
        return clientRepository.findById(codigo).map(this::convertirDTO).orElseThrow(()-> new RuntimeException("Cliente no encontrado: codigo --> " + codigo));
    }



}
