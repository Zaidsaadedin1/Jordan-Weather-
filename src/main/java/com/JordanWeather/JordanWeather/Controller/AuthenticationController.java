package com.JordanWeather.JordanWeather.Controller;
import com.JordanWeather.JordanWeather.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @GetMapping("/login")
    public String showLoginForm() {
        return "Login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("LoginEmail") String email,@RequestParam("LoginPassword") String password,Model model) {
        boolean check = authenticationService.loginUser(email,password);
        if (check) {
            return "redirect:/Home";
        } else {
            model.addAttribute("LoginError", "Login failed. Please try again.");
            return "Login";
        }

    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "Register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("RegisterUsername") String username,
                           @RequestParam("RegisterEmail") String email,
                           @RequestParam("RegisterPassword") String password,
                           Model model) {
        boolean check = authenticationService.registerUser(username, password, email);
        if (check) {
            return "redirect:/Home";
        } else {
            model.addAttribute("registrationError", "Registration failed. Please try again.");
            return "Register";
        }
    }


}
