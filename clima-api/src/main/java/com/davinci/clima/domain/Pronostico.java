package com.davinci.clima.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Pronostico {

	private Clima[] list;
	
	public Clima[] getList() {
		return list;
	}
	public void setList(Clima[] list) {
		this.list = list;
	}
}
