package com.SucursalBBVA.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SucursalBBVA.Model.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
	
	Optional<Empleado> findByNss(String nss);
	
	

}
