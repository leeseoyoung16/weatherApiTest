package com.test.myWeather;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class WeatherService
{
    private final WebClient webClient;

    @Value("${weather.api.key}")
    private String apiKey;

    public WeatherResponse getWeather(String city)
    {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/data/2.5/weather")
                        .queryParam("q", city)
                        .queryParam("appid", apiKey)
                        .queryParam("units", "metric") //섭씨
                        .queryParam("lang", "en")
                        .build())
                .retrieve()
                .bodyToMono(WeatherResponse.class)
                .block();
    }

    public String getCustomWeatherResult(String city) {
        WeatherResponse response = getWeather(city);

        String cityName = response.getName();
        double temp = response.getMain().getTemp();
        String weather = response.getWeather().get(0).getMain();
        String weatherDescription = response.getWeather().get(0).getDescription();

        String message = cityName + "의 날씨는 " + weatherDescription + "이고, 현재 기온은 " + temp + "도 입니다.<br>";

        if(weather.equals("Rain")) {
            message += "우산을 꼭 챙기세요!";
        }else if(temp >= 30) {
            message += "더우니 물을 많이 마시세요!";
        } else if(temp < 5) {
            message += "많이 추우니 따뜻하게 입으세요!";
        }
        return message;
    }
}
