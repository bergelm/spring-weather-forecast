package com.example.springweatherforecast.controller;


import com.example.springweatherforecast.model.ConsolidatedWeather;
import com.example.springweatherforecast.model.Location;
import com.example.springweatherforecast.model.WeatherLocation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class WeatherController {


    private String url = "https://www.metaweather.com/api/location/";
    private static final String DEFAULT_SEARCH_RESULT = "warsaw";

    private WeatherLocation weatherLocation = null;


    public List<Location> getLocation(String search) {

        RestTemplate restTemplate = new RestTemplate();

        if (search.equals("")) {
            search = DEFAULT_SEARCH_RESULT;
        }


        ResponseEntity<Location[]> location = restTemplate.exchange(url + "search/?query=" + search,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Location[].class);
        return Arrays.asList(location.getBody());

    }


    public WeatherLocation getWeatherLocation(String search) {
        RestTemplate restTemplate = new RestTemplate();
        Integer woeid = 523920;

        try {
            woeid = getLocation(search).get(0).getWoeid();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.getStackTrace();
        }

        ResponseEntity<WeatherLocation> weatherForecast = restTemplate.exchange(url + woeid,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                WeatherLocation.class);

        return weatherForecast.getBody();


    }

    @GetMapping("/weather")
    public String getWeather(Model model) {
        String location = "warsaw";
        weatherLocation = getWeatherLocation(location);
        model.addAttribute("weatherLocation", weatherLocation);
        return "weather";
    }

    @PostMapping("/weather-for-location")
    public String getWeatherL(Model model, @RequestParam(name = "search") String search) {
        weatherLocation = getWeatherLocation(search.toLowerCase());
        model.addAttribute("weatherLocation", weatherLocation);
        return "weather";
    }
}





