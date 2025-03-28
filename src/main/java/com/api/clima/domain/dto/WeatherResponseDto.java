package com.api.clima.domain.dto;

public class WeatherResponseDto {

	private int forecast_code;
	private String forecast_description;
	private boolean buyer_notification;
	
	public WeatherResponseDto() {
	}
	
	public WeatherResponseDto(int forecast_code, String forecast_description, boolean buyer_notification) {
		this.forecast_code = forecast_code;
		this.forecast_description = forecast_description;
		this.buyer_notification = buyer_notification;
	}
	
	public int getForecast_code() {
		return forecast_code;
	}
	public void setForecast_code(int forecast_code) {
		this.forecast_code = forecast_code;
	}
	public String getForecast_description() {
		return forecast_description;
	}
	public void setForecast_description(String forecast_description) {
		this.forecast_description = forecast_description;
	}
	public boolean isBuyer_notification() {
		return buyer_notification;
	}
	public void setBuyer_notification(boolean buyer_notification) {
		this.buyer_notification = buyer_notification;
	}	
	
}
