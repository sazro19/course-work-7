package com.vedverma.spring.security.web;

import com.vedverma.spring.security.parking.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/deleteParking")
public class DeleteParkingController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping
    public String deleteParking(@RequestParam String id) {
        parkingService.deleteParking(id);

        return "redirect:/admin";
    }
}
