package com.empleados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empleados.entity.TipoIdentificacion;

@Repository
public interface TipoIdRepository extends JpaRepository<TipoIdentificacion, Integer>{

}
