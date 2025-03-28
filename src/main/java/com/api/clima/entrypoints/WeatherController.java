package com.api.clima.entrypoints;


import com.api.clima.application.ValidateWeatherCase;
import com.api.clima.domain.WeatherResponse;
import com.api.clima.domain.dto.WeatherResponseDto;
import com.api.clima.entrypoints.dto.WeatherRequest;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final ValidateWeatherCase validateWeatherCase;

    public WeatherController(ValidateWeatherCase validateWeatherCase) {
        this.validateWeatherCase = validateWeatherCase;
    }

    @PostMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateWeather(@Valid @RequestBody WeatherRequest weatherRequest) {
    	WeatherResponseDto response = validateWeatherCase.execute(weatherRequest.getEmail(), weatherRequest.getLatitude(), weatherRequest.getLongitude());
        
        if (response == null) {
            return ResponseEntity.ok(Map.of(
                    "forecast_code", -1,
                    "forecast_description", "No es posible consultar clima",
                    "buyer_notification", false));
        }
        return ResponseEntity.ok(Map.of(
                "forecast_code", response.getForecast_code(),
                "forecast_description", response.getForecast_description() ,
                "buyer_notification", response.isBuyer_notification()
        ));
    }
}


