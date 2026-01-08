package com.seuapp.clima.application.service;

import com.seuapp.clima.domain.model.Place;
import com.seuapp.clima.domain.model.WeatherSnapshot;
import com.seuapp.clima.domain.ports.in.GetWeatherUseCase;
import com.seuapp.clima.domain.ports.out.WeatherProviderPort;
import org.springframework.stereotype.Service;

@Service
public class GetWeatherService implements GetWeatherUseCase {

    private final WeatherProviderPort provider;

    public GetWeatherService(WeatherProviderPort provider) {
        this.provider = provider;
    }

    @Override
    public WeatherSnapshot getCurrentWeather(Place place) {
        return provider.fetchCurrent(place);
    }
}

