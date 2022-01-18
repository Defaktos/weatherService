package dbeaver.weather.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dayId;

    private String date;

    private String temperature;

    public Weather() {
    }

    public Weather(String date, String temperature) {
        this.date = date;
        this.temperature = temperature;
    }
}
