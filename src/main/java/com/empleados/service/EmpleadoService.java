package com.empleados.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empleados.entity.Empleado;
import com.empleados.repository.EmpleadoRepository;


@Service
@Transactional
public class EmpleadoService {

	@Autowired
	EmpleadoRepository empleadoRepository;

	public List<Empleado> getEmpleados() {
		return this.empleadoRepository.findAll();
	}

	public Optional<Empleado> getEmpleadoById(int id) {
		return this.empleadoRepository.findById(id);
	}

	public Optional<Empleado> getByNombreEmpleado(String nombre) {
		return empleadoRepository.findByNombre(nombre);
	}
	
	public Optional<Empleado> getByNombreDocumento(String documento) {
		return empleadoRepository.findByDocumento(documento);
	}


	public void guardar(Empleado empleado) {
		empleadoRepository.save(empleado);
	}

	public void eliminar(int id) {
		empleadoRepository.deleteById(id);
	}

	public boolean existePorId(int id) {
		return empleadoRepository.existsById(id);
	}

	public boolean existePorNombre(String nombre) {
		return empleadoRepository.existsByNombre(nombre);
	}
	
	public boolean existePorDocumento(String documento) {
		return empleadoRepository.existsByDocumento(documento);
	}
}
