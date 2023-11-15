package com.JordanWeather.JordanWeather.Controller;
import com.JordanWeather.JordanWeather.Service.HomeService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/Home")
    public String showHomePage(@RequestParam(name = "city", required = false, defaultValue = "Amman") String city, Model model) throws JSONException {
        // Assuming your API response is stored in a variable named "apiResponse"
        String apiResponse = homeService.getWeatherData(city); // Pass the 'city' parameter to the method

        // Parse the JSON response
        JSONObject jsonResponse = new JSONObject(apiResponse);

        // Extract parameters from the JSON response
        JSONObject location = jsonResponse.getJSONObject("location");
        JSONObject current = jsonResponse.getJSONObject("current");

        String cityName = location.getString("name");
        String country = location.getString("country");
        double temperatureC = current.getDouble("temp_c");
        double temperatureF = current.getDouble("temp_f");
        // Extract other parameters as needed...

        // Add extracted parameters to the model for use in the HTML template
        model.addAttribute("cityName", cityName);
        model.addAttribute("country", country);
        model.addAttribute("temperatureC", temperatureC);
        model.addAttribute("temperatureF", temperatureF);
        // Add other parameters to the model as needed...

        return "Home"; // Return the name of your HTML file without the extension
    }

}