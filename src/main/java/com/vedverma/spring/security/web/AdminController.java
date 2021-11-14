package com.vedverma.spring.security.web;

import com.vedverma.spring.security.parking.services.ParkingService;
import com.vedverma.spring.security.web.dto.NewParkingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ParkingService parkingService;

    @ModelAttribute("newParkingForm")
    public NewParkingForm newParkingForm() {
        return new NewParkingForm();
    }

    @GetMapping
    public String showAdminPage(Model model) {
        model.addAttribute("parkingList", parkingService.getAllParking());
        return "admin";
    }

    @PostMapping
    public String createParking(@ModelAttribute("newParkingForm") @Valid NewParkingForm newParkingForm,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            result.getFieldErrors().forEach(fieldError -> {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
            model.addAttribute("errors", errors);
            return showAdminPage(model);
        }

        parkingService.createParking(newParkingForm.getName(),
                newParkingForm.getWidth(),
                newParkingForm.getHeight());

        return "redirect:/admin";
    }
}
