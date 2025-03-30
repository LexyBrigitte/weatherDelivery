package com.api.clima.entrypoints.dto;

import java.time.LocalDateTime;

public class LogNotificationDto {

	private String correo;
	private LocalDateTime fecha;
	private String ubicacion;
	private String desClima;
	
	public LogNotificationDto() {
		
	}
	
	public LogNotificationDto(String correo, LocalDateTime fecha, String ubicacion, String desClima) {
		this.correo = correo;
		this.fecha = fecha;
		this.ubicacion = ubicacion;
		this.desClima = desClima;
	}
	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getDesClima() {
		return desClima;
	}
	public void setDesClima(String desClima) {
		this.desClima = desClima;
	}
	
	
}
