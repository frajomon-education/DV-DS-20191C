package com.davinci.clima.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.davinci.clima.domain.Clima;
import com.davinci.clima.domain.Pronostico;
import com.davinci.clima.domain.DTO.ClimaRequestDTO;
import com.davinci.clima.domain.DTO.ClimaResponseDTO;
import com.davinci.clima.domain.entity.Estadistica;
import com.davinci.clima.repository.EstadisticaRepository;
import com.davinci.clima.service.ClimaService;

@Service
public class ClimaServiceImpl implements ClimaService{
	
	@Autowired
	private EstadisticaRepository estadisticaRepository;

	@Override
	public ClimaResponseDTO clima(ClimaRequestDTO request) throws Exception {
		
		SimpleDateFormat formatoJson = new SimpleDateFormat("yyyy-MM-dd");
		String url = "https://api.openweathermap.org/data/2.5/weather?lat="+request.getLatitud()+
					 "&lon="+request.getLongitud()+"&units=metric&lang=es&appid=724a1215b3bbd96fe1a21c77e211e1aa";
		RestTemplate cliente = new RestTemplate();
		Clima weatherResponse = cliente.getForObject(url, Clima.class);
		
		ClimaResponseDTO response = new ClimaResponseDTO();
		response.setFecha(formatoJson.format(new Date()));
		response.setEstado(weatherResponse.getWeather()[0].getDescription());
		response.setHumedad(weatherResponse.getMain().getHumidity());
		response.setPresion(weatherResponse.getMain().getPressure());
		response.setTemperatura(weatherResponse.getMain().getTemp());
		response.setTemperaturaMinima(weatherResponse.getMain().getTemp_min());
		response.setTemperaturaMaxima(weatherResponse.getMain().getTemp_max());
		response.setUbicacion(weatherResponse.getName());
		response.setIdIcono(weatherResponse.getWeather()[0].getIcon());
				
		return response;
	}

	@Override
	public List<ClimaResponseDTO> pronostico(ClimaRequestDTO request) throws Exception {
		String url = "https://api.openweathermap.org/data/2.5/forecast?lat="+request.getLatitud()+
				 "&lon="+request.getLongitud()+"&units=metric&lang=es&appid=724a1215b3bbd96fe1a21c77e211e1aa";
		RestTemplate cliente = new RestTemplate();
		Pronostico weatherResponse = cliente.getForObject(url, Pronostico.class);
		
		List<ClimaResponseDTO> pronostico = new ArrayList<>();
		
		for(Clima item : weatherResponse.getList()) {
			
			String hora = item.getDt_txt().substring(11, 19);

			if (hora.equalsIgnoreCase("12:00:00")) {
				ClimaResponseDTO dia = new ClimaResponseDTO();
				dia.setFecha(item.getDt_txt().substring(0, 10));
				dia.setEstado(item.getWeather()[0].getDescription());
				dia.setHumedad(item.getMain().getHumidity());
				dia.setPresion(item.getMain().getPressure());
				dia.setTemperatura(item.getMain().getTemp());
				dia.setTemperaturaMinima(item.getMain().getTemp_min());
				dia.setTemperaturaMaxima(item.getMain().getTemp_max());
				dia.setUbicacion("");
				dia.setIdIcono(item.getWeather()[0].getIcon());
				pronostico.add(dia);
			}	
		}
		
		return pronostico;
	}


	@Override
	public List<Estadistica> estadisticas() {
		
		List<Estadistica> estadisticas = new ArrayList<Estadistica>();
		
		LocalDate fecha = LocalDate.now().minusDays(7);
		DecimalFormat df = new DecimalFormat("##.0");
		
	   	for (int i = 1; i <= 7; i++) {
	   		
	   		double tempMin =  Math.round((Math.random()*((10 - 5)+1)) + 5);
	   		double tempMax =  Math.round((Math.random()*((18 - 11)+1)) + 11);
	   		double tempProm = Math.round((tempMin + tempMax) / 2);
	   		
			Estadistica estadistica = new Estadistica();
			estadistica.setFechaHora(fecha);
			estadistica.setTemperatura(tempProm);
			estadistica.setTemperaturaMinima(tempMin);
			estadistica.setTemperaturaMaxima(tempMax);
	
		
			estadisticas.add(estadistica);
			
			fecha = fecha.plusDays(1);
			
	   	}
	   	
	   	return estadisticas;
		
	}
	
	/*SERVICIOS QUE SE UTILIZABAN PARA LAS ESTADISTICAS ANTERIORMENTE.*/
	
	public ClimaResponseDTO estadistica(ClimaRequestDTO request) throws Exception {
		
		SimpleDateFormat formatoJson = new SimpleDateFormat("yyyy-MM-dd");
		String url = "https://api.openweathermap.org/data/2.5/weather?lat="+request.getLatitud()+
				 "&lon="+request.getLongitud()+"&units=metric&lang=es&appid=724a1215b3bbd96fe1a21c77e211e1aa";
		RestTemplate cliente = new RestTemplate();
		Clima weatherResponse = cliente.getForObject(url, Clima.class);
		Long id = weatherResponse.getId();
		
		List<Estadistica> estadisticas = estadisticaRepository.findByIdUbicacion(id);
		ClimaResponseDTO estadistica = new ClimaResponseDTO();
		int cantidad = 0;
		double temperaturaSuma = 0;
		double temperaturaMaximaSuma = 0;
		double temperaturaMinimaSuma = 0;
		double presionSuma = 0;
		int humedadSuma = 0;
		
		for(Estadistica historico : estadisticas) {
			cantidad ++;
			temperaturaSuma += historico.getTemperatura();
			temperaturaMaximaSuma += historico.getTemperaturaMaxima();
			temperaturaMinimaSuma += historico.getTemperaturaMinima();
			presionSuma += historico.getPresion();
			humedadSuma += historico.getHumedad();
		}
		
		if(cantidad > 0) {
			estadistica.setFecha(formatoJson.format(new Date()));
			estadistica.setTemperatura(temperaturaSuma / cantidad); 
			estadistica.setTemperaturaMaxima(temperaturaMaximaSuma / cantidad);
			estadistica.setTemperaturaMinima(temperaturaMinimaSuma / cantidad);
			estadistica.setPresion(presionSuma / cantidad);
			estadistica.setHumedad(humedadSuma / cantidad);
		} else {
			return null;
		}
		
		return estadistica;
	}

	public void guardarEstadistica(Clima weatherResponse) throws Exception {
		
		SimpleDateFormat formatoEntity = new SimpleDateFormat("yyyyMMddHH");
		
		List<Estadistica> estadisticas = estadisticaRepository.findByIdUbicacion(weatherResponse.getId());
		Boolean existe = false;
		/*for (Estadistica historico : estadisticas) {
			if (historico.getFechaHora().equalsIgnoreCase(formatoEntity.format(new Date()))) {
				existe = true;
			}
		}*/
		
		if(!existe) {
			Estadistica estadistica = new Estadistica();
			//estadistica.setFechaHora(formatoEntity.format(new Date()));
			estadistica.setEstado(weatherResponse.getWeather()[0].getDescription());
			estadistica.setHumedad(weatherResponse.getMain().getHumidity());
			estadistica.setPresion(weatherResponse.getMain().getPressure());
			estadistica.setTemperatura(weatherResponse.getMain().getTemp());
			estadistica.setTemperaturaMinima(weatherResponse.getMain().getTemp_min());
			estadistica.setTemperaturaMaxima(weatherResponse.getMain().getTemp_max());
			estadistica.setIdUbicacion(weatherResponse.getId());
			estadistica.setIdIcono(weatherResponse.getWeather()[0].getIcon());
			
			estadisticaRepository.save(estadistica);
		}
		
	}

}
