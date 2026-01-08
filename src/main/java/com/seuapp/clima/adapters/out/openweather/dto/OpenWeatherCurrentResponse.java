package com.seuapp.clima.adapters.out.openweather.dto;

import java.util.List;

public record OpenWeatherCurrentResponse(
        Main main,
        Wind wind,
        List<Weather> weather,
        long dt
) {
    public record Main(double temp, double feels_like, int humidity) {}
    public record Wind(double speed) {}
    public record Weather(String main, String description) {}
}

