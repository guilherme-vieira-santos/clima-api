package com.seuapp.clima.domain.ports.out;

import com.seuapp.clima.domain.model.Place;
import com.seuapp.clima.domain.model.WeatherSnapshot;

public interface WeatherProviderPort {
    WeatherSnapshot fetchCurrent(Place place);
}

