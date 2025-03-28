package com.api.clima.infrastructure.external.clima;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Component
public class WeatherApiExternal {

    private final WebClient webClient;

    @Value("${weatherapi.key}")
    private String apiKey;

    public WeatherApiExternal(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.weatherapi.com/v1").build();
    }

    public Map<String, Object> getWeather(double latitude, double longitude) {
        String url = String.format("/forecast.json?key=%s&q=%f,%f&days=2&aqi=no&alerts=no&lang=es", apiKey, latitude, longitude);
        
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
}
