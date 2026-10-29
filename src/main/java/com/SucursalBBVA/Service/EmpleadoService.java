package com.SucursalBBVA.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SucursalBBVA.Dao.EmpleadoRepository;
import com.SucursalBBVA.Model.Empleado;

@Service
public class EmpleadoService {
	
	@Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> buscarPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    public Empleado guardarEmpleado(Empleado empleado) throws Exception {
    	//Validar si el NSS O NOMBRE ya existe
        Optional<Empleado> existente = empleadoRepository.findByNss(empleado.getNss());
        if (existente.isPresent() && (empleado.getIdEmpleado() == null || !existente.get().getIdEmpleado().equals(empleado.getIdEmpleado()))) {
            throw new RuntimeException("El NSS " + empleado.getNss() + " ya está registrado en el sistema.");
        }
        return empleadoRepository.save(empleado);
    }

    public void eliminarEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }


}
