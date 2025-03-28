package com.api.clima.entrypoints.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class WeatherRequest {
	@NotBlank(message = "El Email es obligatorio")
	@NotNull(message = "El Email es obligatorio")
	@Email(message = "Ingrese un email válido")
	private String email;
		
	@NotNull(message = "La latitud es obligatoria")
	private Double latitude;
		
	@NotNull(message = "La longitud es obligatoria")
	private Double longitude;
	
	public WeatherRequest() {
	}	

	public WeatherRequest(String email, Double latitude, Double longitude) {
		this.email = email;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}	
	
}
