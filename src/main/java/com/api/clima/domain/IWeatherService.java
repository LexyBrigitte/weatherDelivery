package com.api.clima.domain;

import com.api.clima.domain.dto.WeatherResponseDto;

public interface IWeatherService {
	WeatherResponseDto validateWeather(String email, double latitude, double longitude);
}
