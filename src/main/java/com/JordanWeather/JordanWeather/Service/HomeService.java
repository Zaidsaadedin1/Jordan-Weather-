package com.JordanWeather.JordanWeather.Service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HomeService {
    private final String apiKey = "96ae98b686af4532a3514145231411";
    private final String apiUrl = "http://api.weatherapi.com/v1/current.json";

    public String getWeatherData(String city) {
        String url = apiUrl + "?key=" + apiKey + "&q=" + city + "&aqi=no";

        // Set up the RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Set up the request entity with headers
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // Make the HTTP GET request
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        // Retrieve the response body
        String responseBody = responseEntity.getBody();

        try {
            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(responseBody);

            // Process the response as needed
            System.out.println("Response: " + responseBody);

            // Now you can further parse the JSON response and use the data as needed
        } catch (JSONException e) {
            // Handle JSON parsing exception
            e.printStackTrace();
        }

        return responseBody;
    }
}
