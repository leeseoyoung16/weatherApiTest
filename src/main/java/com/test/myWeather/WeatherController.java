package com.test.myWeather;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherController
{
    private final WeatherService weatherService;

    @GetMapping
    public String getWeather(@RequestParam String city) {
        return weatherService.getCustomWeatherResult(city);
    }
}
