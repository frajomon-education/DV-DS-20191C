package com.davinci.clima.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.davinci.clima.domain.entity.Estadistica;

public interface EstadisticaRepository extends CrudRepository<Estadistica, Long>{

	List<Estadistica> findByIdUbicacion(Long id);
}
