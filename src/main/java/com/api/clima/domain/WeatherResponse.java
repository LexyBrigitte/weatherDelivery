package com.api.clima.domain;

public record WeatherResponse(int forecast_code, String forecast_description, boolean buyer_notification) {

}
