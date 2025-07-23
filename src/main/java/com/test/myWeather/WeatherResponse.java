package com.test.myWeather;

import lombok.Data;
import java.util.List;

@Data
public class WeatherResponse
{
    private Main main;
    private List<Weather> weather;
    private String name;

    @Data
    public static class Main {
        private double temp; //온도
        private double humidity; //습도
    }

    @Data
    public static class Weather
    {
        private String main;
        private String description;
    }
}
