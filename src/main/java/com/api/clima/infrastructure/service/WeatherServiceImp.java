package com.api.clima.infrastructure.service;

import com.api.clima.domain.WeatherResponse;
import com.api.clima.domain.dto.WeatherResponseDto;
import com.api.clima.domain.IWeatherService;
import com.api.clima.infrastructure.external.clima.WeatherApiExternal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WeatherServiceImp implements IWeatherService {

    private final WeatherApiExternal weatherApiExternal;
    private final EmailService emailService;
    
    //@Value("${weather.codes}")
    private String weatherCodes;

    public WeatherServiceImp(WeatherApiExternal weatherApiExternal, EmailService emailService,@Value("${weather.codes}") String weatherCodes) {
        this.weatherApiExternal = weatherApiExternal;
        this.emailService = emailService;
        this.weatherCodes = weatherCodes;
    }
    
    public List<Integer> getWeatherCodes() {
        return Arrays.stream(weatherCodes.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @Override
    public WeatherResponseDto validateWeather(String email, double latitude, double longitude) {
        Map<String, Object> weatherResponse = weatherApiExternal.getWeather(latitude, longitude);

        if (weatherResponse == null || !weatherResponse.containsKey("forecast")) {
            throw new RuntimeException("No se puede obtener el clima");
        }

        Map<String, Object> forecast = (Map<String, Object>) ((List<?>) ((Map<String, Object>) weatherResponse.get("forecast")).get("forecastday")).get(1);
        Map<String, Object> day = (Map<String, Object>) forecast.get("day");
        Map<String, Object> condition = (Map<String, Object>) day.get("condition");
        
        int forecast_code = (int) condition.get("code");
        String forecast_description = (String) condition.get("text");

        boolean buyer_notification = getWeatherCodes().contains(forecast_code);

        if (buyer_notification) {
            emailService.sendEmail(email, forecast_description);
        }

        return new WeatherResponseDto(forecast_code, forecast_description, buyer_notification);
    }
}

