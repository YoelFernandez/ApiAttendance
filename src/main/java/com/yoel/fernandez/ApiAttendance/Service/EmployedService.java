package com.yoel.fernandez.ApiAttendance.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yoel.fernandez.ApiAttendance.DTO.EmployedDTO;
import com.yoel.fernandez.ApiAttendance.Entity.Employed;
import com.yoel.fernandez.ApiAttendance.Repositoy.EmployedRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployedService {

    private final EmployedRepository employedRepository;
    public void crearEmpleado(Employed employed){
        employedRepository.save(employed);
    }

    public List<Employed> listarEmpleados(){
        return employedRepository.findAll();
    }

    public List<EmployedDTO> getAllEmployees() {
        List<Employed> empleados = employedRepository.findAll();
        return empleados.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EmployedDTO convertToDTO(Employed employed) {
        return new EmployedDTO(
                employed.getCodigoEmpleado(),
                employed.getNombresEmpleado(),
                employed.getApellidosEmpleado(),
                employed.getPuestoEmpleado(),
                employed.getSueldoEmpleado(),
                employed.getTelefonoEmpleado(),
                employed.getCorreoEmpleado(),
                employed.getPassword(),
                employed.getRole()
        );
    }

    // Método para crear un empleado a partir de un DTO
    public EmployedDTO createEmployeDTO(EmployedDTO employedDTO) {
        Employed employed = new Employed();
        employed.setCodigoEmpleado(employedDTO.getCodigoEmpleado());
        employed.setNombresEmpleado(employedDTO.getNombresEmpleado());
        employed.setApellidosEmpleado(employedDTO.getApellidosEmpleado());
        employed.setPuestoEmpleado(employedDTO.getPuestoEmpleado());
        employed.setSueldoEmpleado(employedDTO.getSueldoEmpleado());
        employed.setTelefonoEmpleado(employedDTO.getTelefonoEmpleado());
        employed.setCorreoEmpleado(employedDTO.getCorreoEmpleado());
        employed.setPassword(employedDTO.getPassword());
        employed.setRole(employedDTO.getRole());
        Employed savedEmployed = employedRepository.save(employed);
        return convertToDTO(savedEmployed);
    }

    public void eliminarEmpleado(String codigo){
        if(employedRepository.existsById(codigo)){
            employedRepository.deleteById(codigo);
        }else{
            throw new RuntimeException("Empleado no encontrado con el codigo: " + codigo);
        }
    }


    public EmployedDTO actualEmployedDTO(String codigo, EmployedDTO employedDTO){
        return employedRepository.findById(codigo).map(empleado ->{
            empleado.setNombresEmpleado(employedDTO.getNombresEmpleado());
            empleado.setApellidosEmpleado(employedDTO.getApellidosEmpleado());
            empleado.setPuestoEmpleado(employedDTO.getPuestoEmpleado());
            empleado.setSueldoEmpleado(employedDTO.getSueldoEmpleado());
            empleado.setTelefonoEmpleado(employedDTO.getTelefonoEmpleado());
            empleado.setCorreoEmpleado(employedDTO.getCorreoEmpleado());

            Employed employedActualizado = employedRepository.save(empleado);
            return convertToDTO(employedActualizado);

        })
        .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + codigo));
    }

}
