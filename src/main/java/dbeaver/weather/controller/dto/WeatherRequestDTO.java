package dbeaver.weather.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WeatherRequestDTO {

    private String date;

    private String temperature;
}
