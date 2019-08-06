package com.davinci.clima.service;

import java.util.List;

import com.davinci.clima.domain.DTO.ClimaRequestDTO;
import com.davinci.clima.domain.DTO.ClimaResponseDTO;
import com.davinci.clima.domain.entity.Estadistica;


public interface ClimaService {

	public ClimaResponseDTO clima(ClimaRequestDTO request) throws Exception;
	public List<ClimaResponseDTO> pronostico(ClimaRequestDTO request) throws Exception;
	public List<Estadistica> estadisticas();
	
}
