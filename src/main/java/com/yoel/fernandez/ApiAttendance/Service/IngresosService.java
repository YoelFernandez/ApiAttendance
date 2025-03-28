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


    public void crearIngreso2(IngresosDTO ingresosDTO) {
        Ingresos ingresos = new Ingresos();
        //ingresos.setCodigoIngreso(ingresosDTO.getCodigoIngreso());
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
            String.valueOf(ingresos.getCodigoIngreso()),
            ingresos.getMontoServicio(),
            ingresos.getMediPago().name(),
            ingresos.getEstadoPago().name(),
            ingresos.getFechaIngresos(),
            String.valueOf(ingresos.getService().getCodigoServicio())
            );
    }

    public List<IngresosDTO> listarIngresosDTO() {
        List<Ingresos> listaIngresos = ingresosRepository.findAll();
        return listaIngresos.stream().map(this::convert).collect(Collectors.toList());
    }

    public void eliminarIngreso(String codigo) {
        if(ingresosRepository.existsById(codigo)){
            ingresosRepository.deleteById(codigo);
        }else{
            throw new RuntimeException("Ingreso no encontrado con el codigo: " + codigo);
        }
    }

    public IngresosDTO actualIngresoDTO(String codigo, IngresosDTO ingresosDTO) {
        return ingresosRepository.findById(codigo).map(ingreso ->{
            ingreso.setEstadoPago(Ingresos.estadoPago.valueOf(ingresosDTO.getEstadoPago()));
            ingreso.setFechaIngresos(ingresosDTO.getFechaIngresos());
            ingreso.setMediPago(Ingresos.medioPago.valueOf(ingresosDTO.getMedioPago()));
            ingreso.setMontoServicio(ingresosDTO.getMontoServicio());

            Ingresos ingresoActualizado = ingresosRepository.save(ingreso);
            return convert(ingresoActualizado);

        })
        .orElseThrow(() -> new RuntimeException("Ingreso no encontrado con ID: " + codigo));
    }

    public IngresosDTO retornarPorId(String codigo) {
        return ingresosRepository.findById(codigo).map(this::convert)
               .orElseThrow(() -> new RuntimeException("Ingreso no encontrado con ID: " + codigo));

    }




}
