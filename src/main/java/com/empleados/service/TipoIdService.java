package com.empleados.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empleados.entity.TipoIdentificacion;
import com.empleados.repository.TipoIdRepository;

@Service
@Transactional
public class TipoIdService {

	@Autowired
	TipoIdRepository tipoIdRepository;
	
	public List<TipoIdentificacion> getAllTipos(){
		return this.tipoIdRepository.findAll();
	}

}
