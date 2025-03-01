package com.yoel.fernandez.ApiAttendance.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.yoel.fernandez.ApiAttendance.DTO.ServiceEntityDTO;
import com.yoel.fernandez.ApiAttendance.Entity.ServiceEntity;
import com.yoel.fernandez.ApiAttendance.Repositoy.ServiceRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ServiceService {
    
    private final ServiceRepository serviceRepository;



    public void crearServicio (ServiceEntity service){
        serviceRepository.save(service);
    }

    public List<ServiceEntity> listarServicios(){
        return serviceRepository.findAll();
    }

    public ServiceEntityDTO convertirDTO(ServiceEntity serviceEntity){
        return  new ServiceEntityDTO(
            serviceEntity.getCodigoServicio(),
            serviceEntity.getNombreServicio(),
            serviceEntity.getTipoServicio().name()
        );
    }

    public List<ServiceEntityDTO> listarServiciosDTO(){
        List<ServiceEntity> listaServicios = serviceRepository.findAll();
        return listaServicios.stream().map(this::convertirDTO).collect(Collectors.toList());
    }
}
