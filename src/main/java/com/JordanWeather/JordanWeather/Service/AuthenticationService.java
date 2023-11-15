package com.JordanWeather.JordanWeather.Service;

import com.JordanWeather.JordanWeather.Model.User;
import com.JordanWeather.JordanWeather.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean registerUser(String username, String password, String email) {
        User user = userRepository.findByEmailAndPassword(email,password);
        if (user == null) {
            User newUser = new User(username, password, email);
            userRepository.save(newUser);
            return true;
        }
        return false;
    }


    public boolean loginUser(String email,String password) {
        User user = userRepository.findByEmailAndPassword(email,password);
        return user != null;
    }
}


