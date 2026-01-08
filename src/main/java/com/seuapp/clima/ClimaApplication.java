package com.seuapp.clima;

import com.seuapp.clima.adapters.out.openweather.OpenWeatherConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(OpenWeatherConfig.class)
public class ClimaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClimaApplication.class, args);
    }
}
