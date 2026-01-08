package com.seuapp.clima.domain.ports.in;

import com.seuapp.clima.domain.model.Place;
import com.seuapp.clima.domain.model.WeatherSnapshot;

public interface GetWeatherUseCase {
    WeatherSnapshot getCurrentWeather(Place place);
}
