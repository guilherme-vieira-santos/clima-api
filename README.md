# 🌤️ Aplicativo de Clima - Weather API

Uma aplicação Spring Boot que consulta informações de clima em tempo real usando a API pública do OpenWeather.

## ✨ Funcionalidades

- 🌍 Consulta de clima por localidade (cidade, estado/país)
- 🌡️ Informações de temperatura (atual, sensação térmica)
- 💨 Dados de vento (velocidade)
- 💧 Umidade relativa do ar
- 📝 Condição e descrição do clima
- 🕐 Horário da última atualização

## 🏗️ Arquitetura

O projeto segue os princípios de **Clean Architecture** com separação clara de responsabilidades:

```
src/
├── adapters/
│   ├── in/
│   │   └── cli/              # Interface CLI
│   └── out/
│       └── openweather/      # Integração com API OpenWeather
├── application/
│   └── service/              # Serviços da aplicação
├── domain/
│   ├── model/                # Modelos de domínio
│   └── ports/                # Interfaces de portas
└── ClimaApplication.java     # Classe principal
```

## 🛠️ Tecnologias

- **Java 21**
- **Spring Boot 3.5.9**
- **Maven**
- **Spring WebFlux** (cliente HTTP reativo)
- **OpenWeather API**

## 📋 Pré-requisitos

- Java 21+
- Maven 3.8+
- Chave de API do OpenWeather (gratuita em https://openweathermap.org/api)

## 🚀 Como usar

### Compilar

```bash
mvn clean package -DskipTests
```

### Executar

```bash
java -jar target/weather-api-0.0.1-SNAPSHOT.jar
```

Ou em um único comando:

```bash
mvn clean package -DskipTests && java -jar target/weather-api-0.0.1-SNAPSHOT.jar
```

### Exemplo de uso

```
🌤  BEM-VINDO AO APLICATIVO DE CLIMA!
=====================================

Digite a localidade (ex: São Paulo,BR) ou 'sair' para encerrar: São Paulo,BR

🌤  CLIMA AGORA
---------------------------
Local:        São Paulo,BR
Temperatura:  25.5 °C
Sensação:     26.1 °C
Umidade:      65 %
Vento:        4.2 m/s
Condição:     Parcialmente nublado
Descrição:    Céu parcialmente nublado
Atualizado:   07/01/2026 22:45:30
---------------------------
```

## ⚙️ Configuração

Edite o arquivo `src/main/resources/application.properties`:

```properties
server.port=8081
openweather.api-key=SUA_CHAVE_API
openweather.base-url=https://api.openweathermap.org
openweather.units=metric
openweather.lang=pt_br
```

## 📝 Estrutura do Projeto

### Domain (Núcleo)
- `Place`: Localidade
- `WeatherSnapshot`: Snapshot do clima
- `GetWeatherUseCase`: Interface do caso de uso
- `WeatherProviderPort`: Interface do provedor

### Application
- `GetWeatherService`: Implementação do caso de uso

### Adapters
- `WeatherCliRunner`: Adaptador de entrada (CLI)
- `OpenWeatherClient`: Adaptador de saída (API)

## 🔄 Fluxo de Dados

```
CLI Input → WeatherCliRunner → GetWeatherUseCase → OpenWeatherClient → API
                                     ↓
                            WeatherSnapshot → CLI Output
```

## 📦 Dependências Principais

- Spring Boot WebFlux
- Spring Boot Validation
- Spring Boot Actuator
- Reactor Netty

## 🐛 Tratamento de Erros

- ❌ Localidade não encontrada
- ❌ Chave de API inválida
- ❌ Erro de conexão com a API
- ❌ Resposta vazia da API

---

**Dica:** Para melhor experiência, use um terminal que suporte emojis!
