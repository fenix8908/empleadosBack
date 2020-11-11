package com.empleados.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empleados.entity.Empleado;
import com.tutorial.crud.entity.Producto;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
	
	Optional<Empleado>findByNombre(String nombre);
	boolean existsByNombre(String nombre);
	
	Optional<Empleado> findByDocumento(String documento);
    boolean existsByDocumento(String documento);
}
