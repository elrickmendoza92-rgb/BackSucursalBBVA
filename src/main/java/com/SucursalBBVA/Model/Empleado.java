package com.SucursalBBVA.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EMPLEADOS")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Empleado {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "ID_EMPLEADO")
	    private Long idEmpleado;

	    @Column(name = "NSS", unique = true, nullable = false, length = 11)
	    private String nss;

	    @Column(name = "NOMBRE", nullable = false, length = 50)
	    private String nombre;

	    @Column(name = "APP", nullable = false, length = 50)
	    private String app;

	    @Column(name = "APM", length = 50)
	    private String apm;

	    @Column(name = "SUELDO", nullable = false)
	    private Double sueldo;

}
