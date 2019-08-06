package com.davinci.clima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.davinci.clima.domain.DTO.ClimaRequestDTO;
import com.davinci.clima.domain.DTO.ClimaResponseDTO;
import com.davinci.clima.domain.entity.Estadistica;
import com.davinci.clima.service.ClimaService;


@Controller
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST})
public class ClimaController {
	
	@Autowired
	private ClimaService service;
		
	
	@GetMapping("/echo")
	public String echo(@RequestParam(value="prueba") String name){
		return name;
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping(value = "/clima", produces = "application/json")
	@ResponseBody
	public ClimaResponseDTO clima(@RequestBody ClimaRequestDTO request) throws Exception {
		return service.clima(request);
	}
	
	@PostMapping(value = "/pronostico", produces = "application/json")
	@ResponseBody
	public List<ClimaResponseDTO> pronostico(@RequestBody ClimaRequestDTO request) throws Exception {
		return service.pronostico(request);
	}
	
	@GetMapping(value="/estadisticas", produces = "application/json")
	@ResponseBody
	public List<Estadistica> estadisticas () {
		return this.service.estadisticas();
	}
}
