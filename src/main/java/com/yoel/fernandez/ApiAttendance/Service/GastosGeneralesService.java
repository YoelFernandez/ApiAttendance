package com.yoel.fernandez.ApiAttendance.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yoel.fernandez.ApiAttendance.DTO.GastosGeneralesDTO;
import com.yoel.fernandez.ApiAttendance.Entity.Employed;
import com.yoel.fernandez.ApiAttendance.Entity.GastosGenerales;
import com.yoel.fernandez.ApiAttendance.Entity.Obra;
import com.yoel.fernandez.ApiAttendance.Repositoy.EmployedRepository;
import com.yoel.fernandez.ApiAttendance.Repositoy.GastosGeneralesRepository;
import com.yoel.fernandez.ApiAttendance.Repositoy.ObraRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class GastosGeneralesService {

    private final GastosGeneralesRepository gastosGeneralesRepository;

    private final ObraRepository obraRepository;
    private final EmployedRepository employedRepository;

    public void nuevoGasto(GastosGenerales gastosGenerales){
        gastosGeneralesRepository.save(gastosGenerales);
    }

    public List<GastosGenerales> listaGastosGenerales(){
        return gastosGeneralesRepository.findAll();
    }

    public void nuevoGastoDTO(GastosGeneralesDTO gastosGeneralesDTO){
        GastosGenerales gastosGenerales = new GastosGenerales();
        gastosGenerales.setIdGastosGenerales(gastosGeneralesDTO.getIdGastosGenerales());
        gastosGenerales.setDescripcionGastosGenerales(gastosGeneralesDTO.getDescripcionGastosGenerales());
        gastosGenerales.setMontoGastosGenerales(gastosGeneralesDTO.getMontoGastosGenerales());
        gastosGenerales.setFechaEntrega(gastosGeneralesDTO.getFechaEntrega());
        gastosGenerales.setMedioPago(GastosGenerales.medioPago.valueOf(gastosGeneralesDTO.getMedioPago()));
        gastosGenerales.setEstadoPago(GastosGenerales.estadoPago.valueOf(gastosGeneralesDTO.getEstadoPago()));
        Obra obra = obraRepository.findById(gastosGeneralesDTO.getCodigoObra()).orElse(null);
        // ServiceEntity servicio = serviceRepository.findById(obraDTO.getServicio().getCodigoServicio())
        gastosGenerales.setObra(obra);
        Employed employed = employedRepository.findById(gastosGeneralesDTO.getCodigoEmpleado()).orElse(null);

        gastosGenerales.setEmpleado(employed);
        gastosGeneralesRepository.save(gastosGenerales);
    }

    public GastosGeneralesDTO convert(GastosGenerales gastosGenerales){
        return new GastosGeneralesDTO(
                gastosGenerales.getIdGastosGenerales(),
                gastosGenerales.getDescripcionGastosGenerales(),
                gastosGenerales.getMontoGastosGenerales(),
                gastosGenerales.getFechaEntrega(), 
                gastosGenerales.getMedioPago().name(),
                gastosGenerales.getEstadoPago().name(),
                gastosGenerales.getObra().getCodObra(),
                gastosGenerales.getEmpleado().getCodigoEmpleado()
            );
    }

    public List<GastosGeneralesDTO> listaGastosGeneralesDTO(){
        List<GastosGenerales> listaGastos = gastosGeneralesRepository.findAll();
        return listaGastos.stream().map(this::convert).collect(Collectors.toList());
    }

}
