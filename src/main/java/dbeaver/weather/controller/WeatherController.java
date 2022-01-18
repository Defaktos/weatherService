package dbeaver.weather.controller;

import dbeaver.weather.controller.dto.WeatherRequestDTO;
import dbeaver.weather.controller.dto.WeatherResponseDTO;
import dbeaver.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public WeatherResponseDTO getWeather() throws IOException {
        return weatherService.getWeather();
    }
}