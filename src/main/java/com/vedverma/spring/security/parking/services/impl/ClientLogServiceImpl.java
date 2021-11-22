package com.vedverma.spring.security.parking.services.impl;

import com.vedverma.spring.security.parking.services.ClientLog;
import com.vedverma.spring.security.parking.services.ClientLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientLogServiceImpl implements ClientLogService {

    private static final String ALL_CLIENT_LOGS = "http://localhost:8080/logClient/statistics/all?%s%s";

    private static final String ALL_PARKING_CLIENT_LOGS = "http://localhost:8080/logClient/statistics?from=%s&to=%s&parkingId=%s";

    private static final String CLIENT_LOG = "http://localhost:8080/logClient/statistics/client?clientIdentifier=%s";

    private static final Logger log = LoggerFactory.getLogger(DefaultParkingService.class);

    @Autowired
    private RestOperations restTemplate;

    @Override
    public List<ClientLog> getAllClientLogs(String from, String to) {
        URI uri = URI.create(String.format(ALL_CLIENT_LOGS,
                from == null ? "" : "from=" + from,
                to == null ? "" : "&to=" + to));

        List<ClientLog> clientLogs = new ArrayList<>();
        try {
            clientLogs = restTemplate.getForObject(uri, List.class);
        } catch (Exception ignored) {
            log.error("Exception", ignored);
        }
        return clientLogs;
    }

    @Override
    public List<ClientLog> getAllParkingClientLogs(String parkingId, String from, String to) {
        URI uri = URI.create(String.format(ALL_PARKING_CLIENT_LOGS,
                from == null ? "" : from,
                to == null ? "" : to,
                parkingId));

        List<ClientLog> clientLogs = new ArrayList<>();
        try {
            clientLogs = restTemplate.getForObject(uri, List.class);
        } catch (Exception ignored) {
            log.error("Exception", ignored);
        }
        return clientLogs;
    }

    @Override
    public List<ClientLog> getClientLog(String clientId) {
        URI uri = URI.create(String.format(CLIENT_LOG, clientId));

        List<ClientLog> clientLogs = new ArrayList<>();
        try {
            clientLogs = restTemplate.getForObject(uri, List.class);
        } catch (Exception ignored) {
            log.error("Exception", ignored);
        }
        return clientLogs;
    }
}
