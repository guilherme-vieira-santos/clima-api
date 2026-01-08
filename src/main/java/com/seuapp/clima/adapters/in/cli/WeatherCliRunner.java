package com.seuapp.clima.adapters.in.cli;

import com.seuapp.clima.domain.model.Place;
import com.seuapp.clima.domain.model.WeatherSnapshot;
import com.seuapp.clima.domain.ports.in.GetWeatherUseCase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component
public class WeatherCliRunner implements CommandLineRunner {

    private final GetWeatherUseCase useCase;

    public WeatherCliRunner(GetWeatherUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void run(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("\n🌤  BEM-VINDO AO APLICATIVO DE CLIMA!");
            System.out.println("=====================================\n");
            
            while (true) {
                System.out.print("Digite a localidade (ex: Sao Paulo, BR) ou 'sair' para encerrar: ");
                String input = scanner.nextLine().trim();
                
                if (input.equalsIgnoreCase("sair")) {
                    System.out.println("Encerrando aplicação...");
                    System.exit(0);
                }
                
                if (input.isEmpty()) {
                    System.out.println("Localidade não pode estar vazia!\n");
                    continue;
                }
                
                try {
                    var condicoes = useCase.getCurrentWeather(new Place(input));
                    exibirClima(condicoes);
                } catch (Exception e) {
                    System.out.println("❌ Erro ao buscar clima: " + e.getMessage() + "\n");
                }
            }
        }
    }

    private void exibirClima(WeatherSnapshot condicoes) {
        var formatador = DateTimeFormatter
                .ofPattern("dd/MM/yyyy HH:mm:ss")
                .withZone(ZoneId.systemDefault());

        System.out.println("\n🌤  CLIMA AGORA");
        System.out.println("---------------------------");
        System.out.println("Local:        " + condicoes.place().value());
        System.out.println("Temperatura:  " + condicoes.temperatureC() + " °C");
        System.out.println("Sensação:     " + condicoes.feelsLikeC() + " °C");
        System.out.println("Umidade:      " + condicoes.humidityPercent() + " %");
        System.out.println("Vento:        " + condicoes.windSpeedMs() + " m/s");
        System.out.println("Condição:     " + condicoes.condition());
        System.out.println("Descrição:    " + condicoes.description());
        System.out.println("Atualizado:   " + formatador.format(condicoes.observedAt()));
        System.out.println("---------------------------");
    }
}
