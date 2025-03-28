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
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ObraService {
    private final ObraRepository obraRepository; 
    private final ClientRepository clientRepository;
    private final ServiceRepository serviceRepository;

    private final ClientService clientService;
    private final ServiceService serviceService;


    public Obra obtenerDatoId(String codigo){
        return obraRepository.findById(codigo).orElse(null);
    }


    public ObraDTO createObra(ObraDTO obraDTO) {
        Client nuevoCliente = new Client();
        //nuevoCliente.setCodCliente(obraDTO.getCliente().getCodCliente());
        nuevoCliente.setNombreCliente(obraDTO.getCliente().getNombreCliente());
        nuevoCliente.setCorreoCliente(obraDTO.getCliente().getCorreoCliente());
        nuevoCliente.setTelefonoCliente(obraDTO.getCliente().getTelefonoCliente());
        
       


        ServiceEntity nuevoServicio = new ServiceEntity();
        //nuevoServicio.setCodigoServicio(obraDTO.getServicio().getCodigoServicio());
        nuevoServicio.setNombreServicio(obraDTO.getServicio().getNombreServicio());
        nuevoServicio.setTipoServicio(ServiceEntity.tipoServicio.valueOf(obraDTO.getServicio().getTipoServicio())); // Convertir String a Enum
                

        // Crear Obra
        Obra obra = new Obra();
        //obra.setCodObra(obraDTO.getCodObra());
        obra.setAbreviatura(obraDTO.getAbreviatura());
        obra.setNombreProyecto(obraDTO.getNombreProyecto());
        obra.setFechaInicio(obraDTO.getFechaInicio());
        obra.setFechaFin(obraDTO.getFechaFin());
        obra.setEstado(Obra.estadoObra.valueOf(obraDTO.getEstado())); // Convertir String a Enum
        obra.setMontoContratado(obraDTO.getMontoContratado());
        obra.setAdicionales(obraDTO.getAdicionales());
        obra.setCliente(clientRepository.save(nuevoCliente));
        obra.setService(serviceRepository.save(nuevoServicio));

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
            String.valueOf(obra.getCodObra()),
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

    public void eliminarObra(String codigo) {
        if(obraRepository.existsById(codigo)){
            obraRepository.deleteById(codigo);
        }else{
            throw new RuntimeException("Obra no encontrado con el codigo: " + codigo);
        }
    }

    public ObraDTO actualObraDTO(String codigo, ObraDTO obraDTO) {
        
    return obraRepository.findById(codigo).map(obra ->{
        obra.setAbreviatura(obraDTO.getAbreviatura());
        obra.setAdicionales(obraDTO.getAdicionales());
        obra.setEstado(Obra.estadoObra.valueOf(obraDTO.getEstado()));
        obra.setFechaFin(obraDTO.getFechaFin());
        obra.setFechaInicio(obraDTO.getFechaInicio());
        obra.setMontoContratado(obraDTO.getMontoContratado());
        obra.setNombreProyecto(obraDTO.getNombreProyecto());

        
        Obra obraActualizado = obraRepository.save(obra);
        return toObraDTO(obraActualizado);

    })
    .orElseThrow(() -> new RuntimeException("Ingreso no encontrado con ID: " + codigo));

    }

    public ObraDTO retornarPorId(String codigo) {
        return obraRepository.findById(codigo).map(this::toObraDTO)
               .orElseThrow(() -> new RuntimeException("Obra no encontrado con ID: " + codigo));
    }

}
