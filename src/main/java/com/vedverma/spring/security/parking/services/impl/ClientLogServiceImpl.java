package com.vedverma.spring.security.parking.services.impl;

import com.vedverma.spring.security.parking.services.ClientLog;
import com.vedverma.spring.security.parking.services.ClientLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientLogServiceImpl implements ClientLogService {

    private static final String ALL_CLIENT_LOGS = "http://localhost:8080/logClient/statistics/all?%s%s";

    private static final String ALL_PARKING_CLIENT_LOGS = "http://localhost:8080/logClient/statistics/?%s%sparkingId=%s";

    private static final String CLIENT_LOG = "http://localhost:8080/logClient/statistics/client?clientIdentifier=%s";

    private static final Logger log = LoggerFactory.getLogger(DefaultParkingService.class);

    @Autowired
    private RestOperations restTemplate;

    @Override
    public List<ClientLog> getAllClientLogs(String from, String to) {
        URI uri = URI.create(String.format(ALL_CLIENT_LOGS,
                from == null || from.isEmpty() ? "" : "from=" + from,
                to == null || to.isEmpty() ? "" : from == null || from.isEmpty() ? "to=" + to : "&to=" + to
        ));

        List<Map<String, Object>> clientLogs = new ArrayList<>();
        try {
            clientLogs = restTemplate.getForObject(uri, List.class);
        } catch (Exception ignored) {
            log.error("Exception", ignored);
        }
        return clientLogs.stream().map(e -> {
            ClientLog log = new ClientLog();
            log.setLotId(UUID.fromString((String) e.get("lotId")));
            log.setAmount(BigDecimal.valueOf((Double) e.get("amount")).setScale(2, RoundingMode.HALF_DOWN));
            log.setClientIdentifier((String) e.get("clientIdentifier"));
            log.setParkingId(UUID.fromString((String) e.get("parkingId")));
            log.setTime(new Timestamp((Long) e.get("time")).toLocalDateTime());
            return log;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ClientLog> getAllParkingClientLogs(String parkingId, String from, String to) {
        URI uri = URI.create(String.format(ALL_PARKING_CLIENT_LOGS,
                from == null || from.isEmpty() ? "" : "from=" + from,
                to == null || to.isEmpty() ? (from == null || from.isEmpty() ? "" : "&") : from == null || from.isEmpty() ? "to=" + to + "&" : "&to=" + to + "&",
                parkingId));

        List<Map<String, Object>> clientLogs = new ArrayList<>();
        try {
            clientLogs = restTemplate.getForObject(uri, List.class);
        } catch (Exception ignored) {
            log.error("Exception", ignored);
        }
        return clientLogs.stream().map(e -> {
            ClientLog log = new ClientLog();
            log.setLotId(UUID.fromString((String) e.get("lotId")));
            log.setAmount(BigDecimal.valueOf((Double) e.get("amount")).setScale(2, RoundingMode.HALF_DOWN));
            log.setClientIdentifier((String) e.get("clientIdentifier"));
            log.setParkingId(UUID.fromString((String) e.get("parkingId")));
            log.setTime(new Timestamp((Long) e.get("time")).toLocalDateTime());
            return log;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ClientLog> getClientLog(String clientId) {
        URI uri = URI.create(String.format(CLIENT_LOG, clientId));

        List<Map<String, Object>> clientLogs = new ArrayList<>();
        try {
            clientLogs = restTemplate.getForObject(uri, List.class);
        } catch (Exception ignored) {
            log.error("Exception", ignored);
        }
        return clientLogs.stream().map(e -> {
            ClientLog log = new ClientLog();
            log.setLotId(UUID.fromString((String) e.get("lotId")));
            log.setAmount(BigDecimal.valueOf((Double) e.get("amount")).setScale(2, RoundingMode.HALF_DOWN));
            log.setClientIdentifier((String) e.get("clientIdentifier"));
            log.setParkingId(UUID.fromString((String) e.get("parkingId")));
            log.setTime(new Timestamp((Long) e.get("time")).toLocalDateTime());
            return log;
        }).collect(Collectors.toList());
    }
}
