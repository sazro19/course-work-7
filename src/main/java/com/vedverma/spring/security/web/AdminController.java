package com.vedverma.spring.security.web;

import com.vedverma.spring.security.parking.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ParkingService parkingService;

    @GetMapping
    public String showAdminPage(Model model) {

        model.addAttribute("parkingList", parkingService.getAllParking());
        return "admin";
    }

    @PostMapping
    public String createParking(@RequestParam String name,
                                Model model) {
        if (name == null || name.isEmpty()) {
            model.addAttribute("error", "");
            return showAdminPage(model);
        }

        parkingService.createParking(name);

        return "redirect:/admin";
    }
}
