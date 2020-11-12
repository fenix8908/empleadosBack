package com.empleados.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empleados.entity.Area;
import com.empleados.repository.AreaRepository;

@Service
@Transactional
public class AreaService {
	
	@Autowired
	AreaRepository areaRepository;
	
	public List<Area> getAllAreas(){
		return this.areaRepository.findAll();
	}

}
