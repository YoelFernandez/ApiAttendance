package com.yoel.fernandez.ApiAttendance.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yoel.fernandez.ApiAttendance.DTO.AsistenciaDTO;
import com.yoel.fernandez.ApiAttendance.Entity.Asistencia;
import com.yoel.fernandez.ApiAttendance.Entity.Employed;
import com.yoel.fernandez.ApiAttendance.Entity.Obra;
import com.yoel.fernandez.ApiAttendance.Repositoy.AsistenciaRepository;
import com.yoel.fernandez.ApiAttendance.Repositoy.EmployedRepository;
import com.yoel.fernandez.ApiAttendance.Repositoy.ObraRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AsistenciaService {
    private final AsistenciaRepository asistenciaRepository;
    private final ObraRepository obraRepository;
    private final EmployedRepository employedRepository;

    private final EmployedService employedService;
    private final ObraService obraService;

    public AsistenciaDTO crearAsistencia(AsistenciaDTO dto) {
        Employed empleado = employedRepository.findById(dto.getEmployed().getCodigoEmpleado())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        Obra obra = obraRepository.findById(dto.getObra().getCodObra())
                .orElseThrow(() -> new RuntimeException("Obra no encontrada"));

        Asistencia asistencia = new Asistencia();
        asistencia.setAlmuerzo(dto.getAlmuerzo());
        asistencia.setFecha(dto.getFecha());
        asistencia.setHoraIngreso(dto.getHoraIngreso());
        asistencia.setHoraSalida(dto.getHoraSalida());
        asistencia.setEmpleado(empleado);
        asistencia.setObra(obra);

        asistencia = asistenciaRepository.save(asistencia);
        return new AsistenciaDTO(
            asistencia.getAlmuerzo(),
            asistencia.getFecha(),
            asistencia.getHoraIngreso(),
            asistencia.getHoraSalida(),
            employedService.convertToDTO(asistencia.getEmpleado()),
            obraService.toObraDTO(asistencia.getObra())
        );

    }


    public List<Asistencia> listarAsistencias(){
        return asistenciaRepository.findAll();
    }


    public List<AsistenciaDTO> listarAsistenciasDTO() {
        List<Asistencia> asistencias = asistenciaRepository.findAll();
        return asistencias.stream().map(this::converDto).collect(Collectors.toList());
    }

    public AsistenciaDTO converDto(Asistencia asistencia){
        AsistenciaDTO asistenciaDTO = new AsistenciaDTO(
            asistencia.getAlmuerzo(),
            asistencia.getFecha(),
            asistencia.getHoraIngreso(),
            asistencia.getHoraSalida(),
            employedService.convertToDTO(asistencia.getEmpleado()),
            obraService.toObraDTO(asistencia.getObra())
        );
        return asistenciaDTO;
    }

}
