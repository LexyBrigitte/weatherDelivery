package com.api.clima.application;

import org.springframework.stereotype.Service;

import com.api.clima.domain.IWeatherService;
import com.api.clima.domain.dto.WeatherResponseDto;


@Service
public class ValidateWeatherCase {
	private final IWeatherService weatherService;

    public ValidateWeatherCase(IWeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public WeatherResponseDto execute(String email, double latitude, double longitude) {
        return weatherService.validateWeather(email, latitude, longitude);
    }
}
