package com.api.clima.infrastructure.persistence;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "log_notificacion")
public class LogNotificationEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String correo;
	private LocalDateTime fecha;
	private String ubicacion;
	
	@Column(name = "des_clima")
	private String desClima;
	
	public LogNotificationEntity() {
		
	}
	
	public LogNotificationEntity(int id, String correo, LocalDateTime fecha, String ubicacion, String desClima) {
		this.id = id;
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
