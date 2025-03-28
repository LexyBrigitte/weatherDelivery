package com.api.clima.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.api.clima.domain.IWeatherService;
import com.api.clima.domain.WeatherResponse;
import com.api.clima.domain.dto.WeatherResponseDto;

@ExtendWith(MockitoExtension.class)
class ValidateWeatherCaseTest {

    @Mock
    private IWeatherService weatherService;

    @InjectMocks
    private ValidateWeatherCase validateWeatherCase;

    private String email;
    private double latitude;
    private double longitude;

    @BeforeEach
    void setUp() {
        email = "lexybrigitte@gmail.com";
        latitude = 4.6097;
        longitude = -74.0817;
    }

    @Test
    void testValidWeatherSuccess() {
    	WeatherResponseDto MapResponse = new WeatherResponseDto(1189, "Niebla moderada",true);

        when(weatherService.validateWeather(email, latitude, longitude)).thenReturn(MapResponse);

        WeatherResponseDto result = validateWeatherCase.execute(email, latitude, longitude);
                
        assertNotNull(result);
                
        assertEquals(1189, result.getForecast_code() );
        assertEquals("Niebla moderada", result.getForecast_description());
        assertEquals(true, result.isBuyer_notification() );
        
        verify(weatherService, times(1)).validateWeather(email, latitude, longitude);
    }

    @Test
    void testWeatherServiceFail() {
        when(weatherService.validateWeather(email, latitude, longitude))
                .thenThrow(new RuntimeException("No se puede obtener el clima"));

        assertThrows(RuntimeException.class, () -> validateWeatherCase.execute(email, latitude, longitude));

        verify(weatherService, times(1)).validateWeather(email, latitude, longitude);
    }
}
