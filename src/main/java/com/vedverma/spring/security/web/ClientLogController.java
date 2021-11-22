package com.vedverma.spring.security.web;

import com.vedverma.spring.security.parking.services.ClientLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/clientLog")
public class ClientLogController {

    @Autowired
    private ClientLogService clientLogService;

    @GetMapping(value = "/{parkingId}")
    public String clientLog(@RequestParam(required = false) String from,
                            @RequestParam(required = false) String to,
                            @PathVariable String parkingId,
                            Model model) {
        model.addAttribute("clientLogs", clientLogService.getAllParkingClientLogs(parkingId, from, to));
        return "clientLog";
    }

    @GetMapping
    public String parkingDetails(@RequestParam(required = false) String from,
                                 @RequestParam(required = false) String to,
                                 Model model) {
        model.addAttribute("clientLogs", clientLogService.getAllClientLogs(from, to));
        return "clientLog";
    }

    @GetMapping(value = "/client")
    public String parkingDetails(@RequestParam String clientId,
                                 Model model) {
        model.addAttribute("clientLogs", clientLogService.getClientLog(clientId));
        return "clientLog";
    }
}
