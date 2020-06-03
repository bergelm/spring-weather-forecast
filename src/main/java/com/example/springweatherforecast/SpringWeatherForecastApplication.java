package com.example.springweatherforecast;

import com.example.springweatherforecast.model.WeatherLocation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringWeatherForecastApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWeatherForecastApplication.class, args);


    }



}
