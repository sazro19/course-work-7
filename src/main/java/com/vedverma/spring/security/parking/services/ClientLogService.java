package com.vedverma.spring.security.parking.services;

import java.util.List;

public interface ClientLogService {
    List<ClientLog> getAllClientLogs(String from, String to);
    List<ClientLog> getAllParkingClientLogs(String parkingId, String from, String to);
    List<ClientLog> getClientLog(String clientId);
}
