package com.vedverma.spring.security.web;

import com.vedverma.spring.security.parking.ParkingUnit;
import com.vedverma.spring.security.parking.services.ParkingService;
import com.vedverma.spring.security.parking.services.ParkingUnitService;
import com.vedverma.spring.security.web.dto.NewParkingForm;
import com.vedverma.spring.security.web.dto.NewParkingUnitForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/parkingDetails")
public class ParkingDetailsController {

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private ParkingUnitService parkingUnitService;

    @ModelAttribute("newParkingUnitForm")
    public NewParkingUnitForm newParkingUnitForm() {
        return new NewParkingUnitForm();
    }

    @GetMapping
    public String parkingDetails(@RequestParam String id, Model model) {
        model.addAttribute("parking", parkingService.getParkingById(id)
                .orElseThrow(IllegalArgumentException::new));
        model.addAttribute("parkingUnitList", parkingUnitService.getAllParkingUnits(id));
        return "parkingDetails";
    }

    @PostMapping
    public String renameParking(@RequestParam String idForRename,
                                @RequestParam String name,
                                Model model) {
        if (name == null || name.isEmpty()) {
            return parkingDetails(idForRename, model);
        }

        parkingService.renameParking(idForRename, name);

        return parkingDetails(idForRename, model);
    }

    @PostMapping(value = "/saveParking")
    public String saveParkingUnit(@ModelAttribute("newParkingUnitForm") @Valid NewParkingUnitForm newParkingUnitForm,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            result.getFieldErrors().forEach(fieldError -> {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
            model.addAttribute("errors", errors);
            return parkingDetails(newParkingUnitForm.getParkingId(), model);
        }
        ParkingUnit parkingUnit = createParkingUnit(newParkingUnitForm);
        parkingUnitService.createParkingUnit(parkingUnit);

        return "redirect:/parkingDetails?id=" + newParkingUnitForm.getParkingId();
    }

    private ParkingUnit createParkingUnit(NewParkingUnitForm newParkingUnitForm) {
        ParkingUnit parkingUnit = new ParkingUnit();
        parkingUnit.setParkingId(UUID.fromString(newParkingUnitForm.getParkingId()));
        parkingUnit.setCell(newParkingUnitForm.getCell());
        parkingUnit.setFloor(newParkingUnitForm.getFloor());
        parkingUnit.setSection(newParkingUnitForm.getSection());
        parkingUnit.setStatus(newParkingUnitForm.getStatus());
        parkingUnit.setType(newParkingUnitForm.getType());

        return parkingUnit;
    }
}
