package com.api.clima.entrypoints;


import com.api.clima.application.LogNotificationCase;
import com.api.clima.application.ValidateWeatherCase;
import com.api.clima.domain.dto.WeatherResponseDto;
import com.api.clima.entrypoints.dto.LogNotificationDto;
import com.api.clima.entrypoints.dto.WeatherRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/weather")
@Tag(name = "WeatherDelivery", description = "API para validar y notificar el clima para entregas pendientes")
public class WeatherController {

    private final ValidateWeatherCase validateWeatherCase;
    private final LogNotificationCase logNotificationCase;

    public WeatherController(ValidateWeatherCase validateWeatherCase, LogNotificationCase logNotificationCase) {
        this.validateWeatherCase = validateWeatherCase;
        this.logNotificationCase = logNotificationCase;
    }

    @PostMapping("/validate")
    @Operation(summary = "Valida el clima para la ubicación", description = "Consulta el clima del día siguiente y envía notificación si es necesario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta exitosa",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(example = "{ \"forecast_code\": 1189, \"forecast_description\": \"Niebla moderada\", \"buyer_notification\": true }"))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
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
    
    @GetMapping("/logNotification/{email}")
    @Operation(summary = "Retorna lista notificaciones", description = "Retorna las notificaciones enviadas a buyer en el pasado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta exitosa",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(example = "{ \"log\": [{ \"correo\": \"lexybrigitte@gmail.com\",\"fecha\": \"2025-03-29T22:09:27\",\"ubicacion\": \"antioquia- 87-78\", \"desClima\": \"Lluvia\" } ]"))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
    public ResponseEntity<Map<String, Object>> getLogNotification(@Valid @PathVariable String email){
    	List<LogNotificationDto> responseLog = logNotificationCase.getLogNotificationCase(email);
    	
    	return ResponseEntity.ok(Map.of(
    			"message", "Consulta exitosa",
    			"log", responseLog
    			));
    }
}


