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



    

    public ServiceEntityDTO convertirDTO(ServiceEntity serviceEntity){
        return  new ServiceEntityDTO(
            String.valueOf(serviceEntity.getCodigoServicio()),
            serviceEntity.getNombreServicio(),
            serviceEntity.getTipoServicio().name()
        );
    }

    public List<ServiceEntityDTO> listarServiciosDTO(){
        List<ServiceEntity> listaServicios = serviceRepository.findAll();
        return listaServicios.stream().map(this::convertirDTO).collect(Collectors.toList());
    }

    public void eliminarServicio(String codigo) {
        if(serviceRepository.existsById(codigo)){
            serviceRepository.deleteById(codigo);
        }else{
            throw new RuntimeException("Servicio no encontrado con el codigo: " + codigo);
        }
    }

    public ServiceEntityDTO actualizarServicioDTO(String codigo, ServiceEntityDTO serviceEntityDTO) {
         return serviceRepository.findById(codigo).map(service ->{
            service.setNombreServicio(serviceEntityDTO.getNombreServicio());
            service.setTipoServicio(ServiceEntity.tipoServicio.valueOf(serviceEntityDTO.getTipoServicio()));

            ServiceEntity serviceActualizado = serviceRepository.save(service);
            return convertirDTO(serviceActualizado);

            })
            .orElseThrow(() -> new RuntimeException("Ingreso no encontrado con ID: " + codigo));

    }

    public ServiceEntityDTO retornarPorId(String codigo) {
        return serviceRepository.findById(codigo).map(this::convertirDTO)
        .orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + codigo));

    }

    public ServiceEntityDTO nuevoServicio(ServiceEntityDTO serviceEntityDTO) {
        ServiceEntity service = new ServiceEntity();
        //service.setCodigoServicio(serviceEntityDTO.getCodigoServicio());
        service.setNombreServicio(serviceEntityDTO.getNombreServicio());
        service.setTipoServicio(ServiceEntity.tipoServicio.valueOf(serviceEntityDTO.getTipoServicio()));
        serviceRepository.save(service);
        return convertirDTO(service);
    }
}
