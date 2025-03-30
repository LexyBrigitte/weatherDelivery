package com.api.clima.application;

import org.springframework.stereotype.Service;

import com.api.clima.domain.ILogNotification;
import com.api.clima.domain.IWeatherService;
import com.api.clima.domain.dto.WeatherResponseDto;
import com.api.clima.entrypoints.dto.LogNotificationDto;
import java.time.LocalDateTime;


@Service
public class ValidateWeatherCase {
	private final IWeatherService weatherService;
	private final ILogNotification logNotification;

    public ValidateWeatherCase(IWeatherService weatherService, ILogNotification logNotification) {
        this.weatherService = weatherService;
        this.logNotification = logNotification;
    }

    public WeatherResponseDto execute(String email, double latitude, double longitude) {
    	WeatherResponseDto response = weatherService.validateWeather(email, latitude, longitude);
    	
    	if (response.isBuyer_notification()){
    		LogNotificationDto logNotificationDto = new LogNotificationDto();
    		logNotificationDto.setCorreo(email);
    		logNotificationDto.setFecha(LocalDateTime.now());
    		logNotificationDto.setUbicacion(response.getRegion()+" en "+latitude +"-"+longitude);
    		logNotificationDto.setDesClima(response.getForecast_description());
    		
    		logNotification.saveLogNotification(logNotificationDto);
    	}
    	
    	return response;
    }
}
