package com.yoel.fernandez.ApiAttendance.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yoel.fernandez.ApiAttendance.DTO.PagoDTO;
import com.yoel.fernandez.ApiAttendance.Entity.Employed;
import com.yoel.fernandez.ApiAttendance.Entity.Pago;
import com.yoel.fernandez.ApiAttendance.Repositoy.EmployedRepository;
import com.yoel.fernandez.ApiAttendance.Repositoy.PagoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagoService {
    private final PagoRepository pagoRepository;
    private final EmployedRepository employedRepository; 

    public void crearPago(Pago pago){
        pagoRepository.save(pago);

    }

   

    public PagoDTO convertPago(Pago pago){
        return new PagoDTO(
            pago.getCodigoPago(),
            pago.getMontoPago(),
            pago.getMedioPago().name(),
            pago.getEstadoPago().name(),
            pago.getFechaPago(),
            pago.getEmpleado().getNombresEmpleado()
        );
    }

    public List<PagoDTO> listaPagosDTO(){
        List<Pago> listaPagos = pagoRepository.findAll();
        return listaPagos.stream().map(this::convertPago).collect(Collectors.toList());
    }
    public void crearPagoDTO(PagoDTO pagoDTO){
        Pago pago = new Pago();
        pago.setCodigoPago(pagoDTO.getCodigoPago());
        pago.setMontoPago(pagoDTO.getMontoPago()); 
        pago.setMedioPago(Pago.medioPago.valueOf(pagoDTO.getMedioPago())); // Convierte String a enum
        pago.setEstadoPago(Pago.estadoPago.valueOf(pagoDTO.getEstadoPago())); // Convierte String a enum
        pago.setFechaPago(pagoDTO.getFechaPago());
        // Busca el empleado por su código
        Employed empleado = employedRepository.findById(pagoDTO.getCodigoEmpleado())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        pago.setEmpleado(empleado);
        pagoRepository.save(pago);

    }

    public void eliminarPago(String codigo) {
        if(pagoRepository.existsById(codigo)){
            pagoRepository.deleteById(codigo);
        }else{
            throw new RuntimeException("Pago no encontrado con el codigo: " + codigo);
        }
    }

    public PagoDTO actualPagoDTO(String codigo, PagoDTO pagoDTO) {
        return pagoRepository.findById(codigo).map(pago ->{
            pago.setEstadoPago(Pago.estadoPago.valueOf(pagoDTO.getEstadoPago()));
            pago.setFechaPago(pagoDTO.getFechaPago());
            pago.setMedioPago(Pago.medioPago.valueOf(pagoDTO.getMedioPago()));
            pago.setMontoPago(pagoDTO.getMontoPago());

            Pago pagoActualizado = pagoRepository.save(pago);
            return convertPago(pagoActualizado);

            })
            .orElseThrow(() -> new RuntimeException("Ingreso no encontrado con ID: " + codigo));
    }

    public PagoDTO retornarPorId(String codigo) {
        return pagoRepository.findById(codigo).map(this::convertPago)
        .orElseThrow(() -> new RuntimeException("Pago no encontrado con ID: " + codigo));

    }

}
