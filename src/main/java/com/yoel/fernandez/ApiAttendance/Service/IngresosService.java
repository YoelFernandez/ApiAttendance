package com.yoel.fernandez.ApiAttendance.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yoel.fernandez.ApiAttendance.DTO.IngresosDTO;
import com.yoel.fernandez.ApiAttendance.Entity.Ingresos;
import com.yoel.fernandez.ApiAttendance.Entity.ServiceEntity;
import com.yoel.fernandez.ApiAttendance.Repositoy.IngresosRepository;
import com.yoel.fernandez.ApiAttendance.Repositoy.ServiceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngresosService {
    private final IngresosRepository ingresosRepository;
    private final ServiceRepository serviceRepository;

    public void crearIngreso(Ingresos ingresos){
        ingresosRepository.save(ingresos);
    }

    public List<Ingresos> listarIngresos(){
        return ingresosRepository.findAll();
    }


    public void crearIngreso2(IngresosDTO ingresosDTO) {
        Ingresos ingresos = new Ingresos();
        ingresos.setCodigoIngreso(ingresosDTO.getCodigoIngreso());
        ingresos.setMontoServicio(ingresosDTO.getMontoServicio());
        ingresos.setMediPago(Ingresos.medioPago.valueOf(ingresosDTO.getMedioPago()));
        ingresos.setEstadoPago(Ingresos.estadoPago.valueOf(ingresosDTO.getEstadoPago()));
        ingresos.setFechaIngresos(ingresosDTO.getFechaIngresos());

        ServiceEntity service = serviceRepository.findById(ingresosDTO.getCodigoServicio())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        ingresos.setService(service);
        ingresosRepository.save(ingresos);
    }

    public IngresosDTO convert(Ingresos ingresos){
        return new IngresosDTO(
            ingresos.getCodigoIngreso(),
            ingresos.getMontoServicio(),
            ingresos.getMediPago().name(),
            ingresos.getEstadoPago().name(),
            ingresos.getFechaIngresos(),
            ingresos.getService().getCodigoServicio()
            );
    }

    public List<IngresosDTO> listarIngresosDTO() {
        List<Ingresos> listaIngresos = ingresosRepository.findAll();
        return listaIngresos.stream().map(this::convert).collect(Collectors.toList());
    }


}
