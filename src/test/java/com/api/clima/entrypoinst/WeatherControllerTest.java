package com.api.clima.entrypoinst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.api.clima.application.ValidateWeatherCase;
import com.api.clima.entrypoints.WeatherController;
import com.api.clima.entrypoints.dto.WeatherRequest;
import com.api.clima.domain.dto.WeatherResponseDto;

//@WebMvcTest(WeatherControllerTest.class)
@ExtendWith(MockitoExtension.class)
class WeatherControllerTest {

	@Mock private ValidateWeatherCase validateWeatherCase;
	
    @InjectMocks private WeatherController weatherController;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void WeatherResponseApi() throws Exception {
    	WeatherRequest weatherRequest = new WeatherRequest ("lexybrigitte@gmail.com",4.6097,-74.0817);
    	
    	WeatherResponseDto weatherResponse = new WeatherResponseDto(1189, "Niebla moderada",true);
    	
    	lenient().when(validateWeatherCase.execute(weatherRequest.getEmail(), weatherRequest.getLatitude(), weatherRequest.getLongitude())).thenReturn(weatherResponse);

        ResponseEntity<Map<String, Object>> result = weatherController.validateWeather(weatherRequest);
        
        assertNotNull(result);
        
        assertEquals(-1, result.getBody().get("forecast_code"));
        assertEquals("No es posible consultar clima", result.getBody().get("forecast_description"));
        assertEquals(false, result.getBody().get("buyer_notification"));
    }
}
