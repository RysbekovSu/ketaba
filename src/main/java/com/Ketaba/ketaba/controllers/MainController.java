package com.Ketaba.ketaba.controllers;

import com.Ketaba.ketaba.models.Hotel;
import com.Ketaba.ketaba.repo.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;


@Controller
public class MainController {
    @Autowired
    HotelRepository hotelRepository;
    @GetMapping("/")
    public String main(Map<String, Object> model, Authentication authentication){
        Iterable<Hotel> hotels = hotelRepository.findAll();
        model.put("hotels", hotels);
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));
        model.put("admin", isAdmin);
        boolean isUser = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("USER"));
        model.put("user", isUser);
        return "Hotels";

    }



}
