package com.seuapp.clima.adapters.out.openweather;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "openweather")
public record OpenWeatherConfig(
        String baseUrl,
        String apiKey,
        String units,
        String lang
) {}
