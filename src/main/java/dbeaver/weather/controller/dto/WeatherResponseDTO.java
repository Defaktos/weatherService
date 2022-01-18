package dbeaver.weather.controller.dto;

import dbeaver.weather.entity.Weather;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WeatherResponseDTO {

    private String temperature;

    public WeatherResponseDTO(Weather weather) {
        temperature = weather.getTemperature();
    }
}
