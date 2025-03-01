package com.yoel.fernandez.ApiAttendance.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yoel.fernandez.ApiAttendance.DTO.ObraDTO;
import com.yoel.fernandez.ApiAttendance.Entity.Client;
import com.yoel.fernandez.ApiAttendance.Entity.Obra;
import com.yoel.fernandez.ApiAttendance.Entity.ServiceEntity;
import com.yoel.fernandez.ApiAttendance.Repositoy.ClientRepository;
import com.yoel.fernandez.ApiAttendance.Repositoy.ObraRepository;
import com.yoel.fernandez.ApiAttendance.Repositoy.ServiceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObraService {
    private final ObraRepository obraRepository; 
    private final ClientRepository clientRepository;
    private final ServiceRepository serviceRepository;

    private final ClientService clientService;
    private final ServiceService serviceService;


    public void crearObra(Obra obra){ 
        obraRepository.save(obra);
    }

    public List<Obra> listarObras(){
        return obraRepository.findAll();
    }

    public Obra obtenerDatoId(String codigo){
        return obraRepository.findById(codigo).orElse(null);
    }


    public ObraDTO createObra(ObraDTO obraDTO) {
        // Buscar Cliente o crearlo si no existe
        Client cliente = clientRepository.findById(obraDTO.getCliente().getCodCliente())
                .orElseGet(() -> {
                    Client nuevoCliente = new Client();
                    nuevoCliente.setCodCliente(obraDTO.getCliente().getCodCliente());
                    nuevoCliente.setNombreCliente(obraDTO.getCliente().getNombreCliente());
                    nuevoCliente.setCorreoCliente(obraDTO.getCliente().getCorreoCliente());
                    nuevoCliente.setTelefonoCliente(obraDTO.getCliente().getTelefonoCliente());
                    return clientRepository.save(nuevoCliente);
                });

        // Buscar Servicio o crearlo si no existe
        ServiceEntity servicio = serviceRepository.findById(obraDTO.getServicio().getCodigoServicio())
                .orElseGet(() -> {
                    ServiceEntity nuevoServicio = new ServiceEntity();
                    nuevoServicio.setCodigoServicio(obraDTO.getServicio().getCodigoServicio());
                    nuevoServicio.setNombreServicio(obraDTO.getServicio().getNombreServicio());
                    nuevoServicio.setTipoServicio(ServiceEntity.tipoServicio.valueOf(obraDTO.getServicio().getTipoServicio())); // Convertir String a Enum
                    return serviceRepository.save(nuevoServicio);
                });

        // Crear Obra
        Obra obra = new Obra();
        obra.setCodObra(obraDTO.getCodObra());
        obra.setAbreviatura(obraDTO.getAbreviatura());
        obra.setNombreProyecto(obraDTO.getNombreProyecto());
        obra.setFechaInicio(obraDTO.getFechaInicio());
        obra.setFechaFin(obraDTO.getFechaFin());
        obra.setEstado(Obra.estadoObra.valueOf(obraDTO.getEstado())); // Convertir String a Enum
        obra.setMontoContratado(obraDTO.getMontoContratado());
        obra.setAdicionales(obraDTO.getAdicionales());
        obra.setCliente(cliente);
        obra.setService(servicio);

        obraRepository.save(obra); // Guardar en BD

        return obraDTO; // Retornar DTO como respuesta
    }

    public ObraDTO toObraDTO(Obra obra) {
        Client cliente = obra.getCliente();
        if (cliente == null) {
            // Manejar el caso donde el cliente es nulo, por ejemplo, lanzando una excepción o asignando un valor por defecto
            throw new IllegalArgumentException("La obra no tiene un cliente asociado.");
        }
        return new ObraDTO(
            obra.getCodObra(),
            obra.getAbreviatura(),
            obra.getNombreProyecto(),
            obra.getFechaInicio(),
            obra.getFechaFin(),
            obra.getEstado().name(),
            obra.getMontoContratado(),
            obra.getAdicionales(),
            clientService.convertirDTO(cliente),
            serviceService.convertirDTO(obra.getService())
        );
    }
    public List<ObraDTO> listarObrasDTO(){
        List<Obra> listaObras = obraRepository.findAll();
        return listaObras.stream().map(this::toObraDTO).collect(Collectors.toList());
    }

}
