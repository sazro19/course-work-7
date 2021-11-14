package com.vedverma.spring.security.parking.services;

import com.vedverma.spring.security.parking.Parking;

import java.util.List;
import java.util.Optional;

public interface ParkingService {
    List<Parking> getAllParking();
    Optional<Parking> getParkingById(String id);
    void createParking(String name, Integer width, Integer height);
    void deleteParking(String id);
    void renameParking(String id, String name);
}
