package com.vedverma.spring.security.web;

import com.vedverma.spring.security.parking.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/parkingDetails")
public class ParkingDetailsController {

    @Autowired
    private ParkingService parkingService;

    @GetMapping
    public String parkingDetails(@RequestParam String id, Model model) {
        model.addAttribute("parking", parkingService.getParkingById(id)
                .orElseThrow(IllegalArgumentException::new));
        return "parkingDetails";
    }

    @PostMapping
    public String renameParking(@RequestParam String idForRename,
                                @RequestParam String name,
                                Model model) {
        if (name == null || name.isEmpty()) {
            return "parkingDetails";
        }

        parkingService.renameParking(idForRename, name);

        return parkingDetails(idForRename, model);
    }
}
