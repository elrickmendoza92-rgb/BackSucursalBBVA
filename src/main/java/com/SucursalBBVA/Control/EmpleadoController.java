package com.SucursalBBVA.Control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SucursalBBVA.Model.Empleado;
import com.SucursalBBVA.Service.EmpleadoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
	
	@Autowired
    private EmpleadoService empleadoService;

	//Obtener todos los empleados (GET):
	//http://localhost:8000/api/empleados
    @GetMapping
    public List<Empleado> obtenerTodos() {
        return empleadoService.listarEmpleados();
    }

    //Buscar empleado por ID (GET):
    // http://localhost:8000/api/empleados/{id} (reemplaza {id} por el número, ej. 1)
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerPorId(@PathVariable("id") Long id) {
        Optional<Empleado> empleado = empleadoService.buscarPorId(id);
        return empleado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear un empleado nuevo con validación de NSS (POST):
    // http://localhost:8000/api/empleados
    //	(Recuerda enviar el JSON en el Body con: nss, nombre, app, apm y sueldo)
    @PostMapping
    public ResponseEntity<?> crearEmpleado(@RequestBody Empleado empleado) {
        try {
            Empleado nuevoEmpleado = empleadoService.guardarEmpleado(empleado);
            return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    
   /* @PostMapping
    public ResponseEntity<?> guardarEmpleado(@RequestBody Empleado empleado) {
        try {
            Empleado nuevoEmpleado = empleadoService.guardarEmpleado(empleado);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
        } catch (IllegalArgumentException e) {
            // Aquí le regresamos el mensaje exacto a Postman
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
*/
    
    // Actualizar un empleado (PUT):
   // http://localhost:8000/api/empleados/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@PathVariable("id") Long id, @RequestBody Empleado empleadoDetails) {
        try {
            Optional<Empleado> empleadoOpt = empleadoService.buscarPorId(id);
            if (!empleadoOpt.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            Empleado empleado = empleadoOpt.get();
            empleado.setNss(empleadoDetails.getNss());
            empleado.setNombre(empleadoDetails.getNombre());
            empleado.setApp(empleadoDetails.getApp());
            empleado.setApm(empleadoDetails.getApm());
            empleado.setSueldo(empleadoDetails.getSueldo());

            Empleado actualizado = empleadoService.guardarEmpleado(empleado);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    //Eliminar un empleado (DELETE):
    // http://localhost:8000/api/empleados/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable("id") Long id) {
        if (!empleadoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        empleadoService.eliminarEmpleado(id);
        return ResponseEntity.noContent().build();
    }
}
