package dbeaver.weather.service;

import dbeaver.weather.controller.dto.WeatherResponseDTO;
import dbeaver.weather.entity.Weather;
import dbeaver.weather.exception.WeatherNotFoundException;
import dbeaver.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public WeatherResponseDTO getWeather() throws IOException {
        String currentDate = (String) Calendar.getInstance().getTime().toString();
        //Optional<Weather> temperatureByDay = weatherRepository.getTemperatureByDay(currentDate);
        Optional<Weather> temperatureByDay = Optional.empty();
        if (!temperatureByDay.isPresent()) {
            Weather weather = new Weather(currentDate, parse());
            weatherRepository.save(weather);
            return mapToDTO(weather);
        } else {
            Weather weather = temperatureByDay.get();
            return mapToDTO(weather);
        }
    }

    private WeatherResponseDTO mapToDTO(Weather weather) { //нужен ли этот метод или сразу можем в getWeather возвращать новую дто?
        return new WeatherResponseDTO(weather);
    }

    private String parse() throws IOException {
        String html = getHtmlPage();
        Pattern pattern = Pattern.compile("-?.(\\d+)\\b°"); //класс pattern - это само регулярное выражение  (-|\\+ - либо плюс, либо минус перед числом. ? - ноль или более символов до числа. \\d+ одно или более чисел)
        Matcher matcher = pattern.matcher(html); //matcher использует наш pattern чтобы проводить операции над нашей строкой
        String text = null;
        while (matcher.find()) {
            text = matcher.group();
        }
        return text;
    }

    private String getHtmlPage() throws IOException {
        String content = null;
        URLConnection connection = null;
        try {
            connection = new URL("https://yandex.ru").openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
