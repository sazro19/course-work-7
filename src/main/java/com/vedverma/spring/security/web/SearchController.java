package com.vedverma.spring.security.web;

import com.vedverma.spring.security.parking.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ParkingService parkingService;

    @GetMapping
    public String search(@RequestParam(required = false) String id,
                         Model model) {
        if (id != null) {
            model.addAttribute("parking", parkingService.getParkingById(UUID.fromString(id)).
                    orElseThrow(IllegalArgumentException::new));
        }

        return "searchParking";
    }
}
