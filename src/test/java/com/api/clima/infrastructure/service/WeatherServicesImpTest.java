package com.api.clima.infrastructure.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;

import com.api.clima.domain.WeatherResponse;
import com.api.clima.domain.dto.WeatherResponseDto;
import com.api.clima.infrastructure.external.clima.WeatherApiExternal;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = "weather.codes=100,200,300")
class WeatherServicesImpTest {
	
	@Mock private WeatherApiExternal weatherApiExternal;
	@Mock private EmailService emailService;
	
	/*@InjectMocks */private WeatherServiceImp weatherServiceImp;
    
	private String email;
    private double latitude;
    private double longitude;
    private String weatherCodes;
    
    @BeforeEach
    void setUp() {
    	email = "lexybrigitte@gmail.com";
        latitude = 4.6097;
        longitude = -74.0817;
        weatherCodes = "1186,1189,1192,1195";
        
        weatherServiceImp = new WeatherServiceImp(weatherApiExternal, emailService, weatherCodes);
    }
	
	@Test
	void validateWeatherEmailTest() {
		//WeatherResponse weatherResponse = new WeatherResponse (1189, "Niebla moderada",true);
			    
		
		/*List<Integer> getWeatherCodes =  Arrays.stream(weatherCodes.split(","))
				.map(Integer::parseInt)
                .collect(Collectors.toList());*/	    
		
		Map<String, Object> externalResponse = Map.of(
		    "forecast", Map.of(
		        "forecastday", List.of(
		            Map.of("day", Map.of(
		                "condition", Map.of("code", 2000, "text", "Soleado")
		            )),
		            Map.of("day", Map.of(
		                "condition", Map.of("code", 1189, "text", "Niebla moderada")
		            ))
		        )
		    )
		);
		
		when(weatherApiExternal.getWeather(latitude,longitude)).thenReturn(externalResponse);
		
		WeatherResponseDto result = weatherServiceImp.validateWeather(email,latitude,longitude);
		//Se validan los datos moqueados
        assertEquals(1189, result.getForecast_code());
        assertEquals("Niebla moderada", result.getForecast_description());
        assertEquals(true, result.isBuyer_notification());
        
        verify(emailService, times(1)).sendEmail(email, result.getForecast_description());
		
	}
	
	@Test
	void validateWeatherNoEmailTest() {	    
		
		Map<String, Object> externalResponse = Map.of(
		    "forecast", Map.of(
		        "forecastday", List.of(
		            Map.of("day", Map.of(
		            	"condition", Map.of("code", 1189, "text", "Niebla moderada")
		            )),
		            Map.of("day", Map.of(
		            	"condition", Map.of("code", 2000, "text", "Soleado")
		            ))
		        )
		    )
		);
		
		when(weatherApiExternal.getWeather(latitude,longitude)).thenReturn(externalResponse);
		
		WeatherResponseDto result = weatherServiceImp.validateWeather(email,latitude,longitude);
		//Se validan los datos moqueados
        assertEquals(2000, result.getForecast_code());
        assertEquals("Soleado", result.getForecast_description());
        assertFalse(result.isBuyer_notification());
        
        verify(emailService, never()).sendEmail(email, result.getForecast_description());
	}
	
	@Test
    void validateWeatherTestFail() {
        when(weatherApiExternal.getWeather(latitude,longitude))
                .thenThrow(new RuntimeException("No se puede obtener el clima"));

        assertThrows(RuntimeException.class, () -> weatherServiceImp.validateWeather(email, latitude, longitude));

    }

}
