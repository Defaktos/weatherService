package dbeaver.weather.repository;

import dbeaver.weather.entity.Weather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {
    //Optional<Weather> getTemperatureByDay(String date);
}
