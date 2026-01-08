package com.seuapp.clima.adapters.out.openweather;

import com.seuapp.clima.adapters.out.openweather.dto.OpenWeatherCurrentResponse;
import com.seuapp.clima.domain.model.Place;
import com.seuapp.clima.domain.model.WeatherSnapshot;
import com.seuapp.clima.domain.ports.out.WeatherProviderPort;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Instant;

@Component
public class OpenWeatherClient implements WeatherProviderPort {

    private final WebClient webClient;
    private final OpenWeatherConfig config;

    public OpenWeatherClient(WebClient webClient, OpenWeatherConfig config) {
        this.config = config;
        this.webClient = webClient.mutate()
                .baseUrl(config.baseUrl())
                .build();
    }

    @Override
    public WeatherSnapshot fetchCurrent(Place place) {
        try {
            var resposta = webClient.get()
                    .uri(uri -> uri
                            .path("/data/2.5/weather")
                            .queryParam("q", place.value())
                            .queryParam("appid", config.apiKey())
                            .queryParam("units", config.units())
                            .queryParam("lang", config.lang())
                            .build())
                    .retrieve()
                    .bodyToMono(OpenWeatherCurrentResponse.class)
                    .block();

            if (resposta == null) {
                throw new RuntimeException("Resposta vazia da API");
            }

            var condicoes = resposta.weather().get(0);

            return new WeatherSnapshot(
                    place,
                    resposta.main().temp(),
                    resposta.main().feels_like(),
                    resposta.main().humidity(),
                    resposta.wind().speed(),
                    condicoes.main(),
                    condicoes.description(),
                    Instant.ofEpochSecond(resposta.dt())
            );
        } catch (WebClientResponseException.NotFound e) {
            throw new RuntimeException("Localidade não encontrada: " + place.value(), e);
        } catch (WebClientResponseException.Unauthorized e) {
            throw new RuntimeException("Chave de API inválida", e);
        } catch (WebClientResponseException e) {
            throw new RuntimeException("Erro na requisição: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar dados: " + e.getMessage(), e);
        }
    }
}

