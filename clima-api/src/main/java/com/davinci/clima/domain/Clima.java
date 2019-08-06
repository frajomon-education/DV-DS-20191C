package com.davinci.clima.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Clima {
	
	private General main;
	private Estado[] weather;
	private String dt_txt;
	private Long id;
	private String name;
	
	public General getMain() {
		return main;
	}
	public void setMain(General main) {
		this.main = main;
	}
	public Estado[] getWeather() {
		return weather;
	}
	public void setWeather(Estado[] weather) {
		this.weather = weather;
	}
	public String getDt_txt() {
		return dt_txt;
	}
	public void setDt_txt(String dt_txt) {
		this.dt_txt = dt_txt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
