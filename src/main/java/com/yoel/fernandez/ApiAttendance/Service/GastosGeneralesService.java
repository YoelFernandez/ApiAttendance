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

   
    public void nuevoGastoDTO(GastosGeneralesDTO gastosGeneralesDTO){
        GastosGenerales gastosGenerales = new GastosGenerales();
        //gastosGenerales.setIdGastosGenerales(gastosGeneralesDTO.getIdGastosGenerales());
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
                String.valueOf(gastosGenerales.getIdGastosGenerales()),
                gastosGenerales.getDescripcionGastosGenerales(),
                gastosGenerales.getMontoGastosGenerales(),
                gastosGenerales.getFechaEntrega(), 
                gastosGenerales.getMedioPago().name(),
                gastosGenerales.getEstadoPago().name(),
                String.valueOf(gastosGenerales.getObra().getCodObra()),
                String.valueOf(gastosGenerales.getEmpleado().getCodigoEmpleado())
            );
    }

    public List<GastosGeneralesDTO> listaGastosGeneralesDTO(){
        List<GastosGenerales> listaGastos = gastosGeneralesRepository.findAll();
        return listaGastos.stream().map(this::convert).collect(Collectors.toList());
    }

    public void eliminarGasto(Integer codigo) {
        if(gastosGeneralesRepository.existsById(codigo)){
            gastosGeneralesRepository.deleteById(codigo);
        }else{
            throw new RuntimeException("Gasto no encontrado con el codigo: " + codigo);
        }
    }

    public GastosGeneralesDTO actualGastoGeneralDTO(Integer codigo, GastosGeneralesDTO gastosGeneralesDTO) {
        return gastosGeneralesRepository.findById(codigo).map(gasto ->{
            gasto.setDescripcionGastosGenerales(gastosGeneralesDTO.getDescripcionGastosGenerales());
            gasto.setEstadoPago(GastosGenerales.estadoPago.valueOf(gastosGeneralesDTO.getEstadoPago()));
            gasto.setFechaEntrega(gastosGeneralesDTO.getFechaEntrega());
            gasto.setMedioPago(GastosGenerales.medioPago.valueOf(gastosGeneralesDTO.getMedioPago()));   
            gasto.setMontoGastosGenerales(gastosGeneralesDTO.getMontoGastosGenerales());

            GastosGenerales gastoActualizado = gastosGeneralesRepository.save(gasto);
            return convert(gastoActualizado);

        })
        .orElseThrow(() -> new RuntimeException("Gasto no encontrado con ID: " + codigo));
    }

    public GastosGeneralesDTO retornarPorId(Integer codigo) {
        return gastosGeneralesRepository.findById(codigo).map(this::convert).orElseThrow(() -> new RuntimeException("Gasto no encontrado con ID: " + codigo));
    }

}
