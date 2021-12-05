package com.vedverma.spring.security.web;

import com.vedverma.spring.security.parking.services.ClientLog;
import com.vedverma.spring.security.parking.services.ClientLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

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
        List<ClientLog> logs = clientLogService.getAllParkingClientLogs(parkingId, from, to);
        model.addAttribute("clientLogs", logs);
        model.addAttribute("total", logs.stream()
                .mapToDouble(e -> e.getAmount().doubleValue()).sum());
        return "clientLog";
    }

    @GetMapping
    public String parkingDetails(@RequestParam(required = false) String from,
                                 @RequestParam(required = false) String to,
                                 Model model) {
        List<ClientLog> logs = clientLogService.getAllClientLogs(from, to);
        model.addAttribute("clientLogs", logs);
        model.addAttribute("total", logs.stream()
                .mapToDouble(e -> e.getAmount().doubleValue()).sum());
        return "clientLog";
    }

    @GetMapping(value = "/client")
    public String parkingDetails(@RequestParam String clientId,
                                 Model model) {
        List<ClientLog> logs = clientLogService.getClientLog(clientId);
        model.addAttribute("clientLogs", logs);
        model.addAttribute("total", logs.stream()
                .mapToDouble(e -> e.getAmount().doubleValue()).sum());
        return "clientLog";
    }
}
