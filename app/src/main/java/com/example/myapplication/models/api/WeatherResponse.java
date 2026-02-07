package com.example.myapplication.models.api;

public class WeatherResponse {
    private String name;
    private Main main;
    private Weather[] weather;

    public String getName() { return name; }
    public Main getMain() { return main; }
    public Weather[] getWeather() { return weather; }

    public static class Main {
        private double temp;
        public double getTemp() { return temp; }
    }

    public static class Weather {
        private String description;
        private String icon;
        public String getDescription() { return description; }
        public String getIcon() { return icon; }
    }
}
