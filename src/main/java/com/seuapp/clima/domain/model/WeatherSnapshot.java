package com.seuapp.clima.domain.model;

import java.time.Instant;

public record WeatherSnapshot(
        Place place,
        double temperatureC,
        double feelsLikeC,
        int humidityPercent,
        double windSpeedMs,
        String condition,
        String description,
        Instant observedAt
) {}
