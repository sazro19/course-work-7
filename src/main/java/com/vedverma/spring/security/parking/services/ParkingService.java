package com.vedverma.spring.security.parking.services;

import com.vedverma.spring.security.parking.Parking;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParkingService {
    List<Parking> getAllParking();
    Optional<Parking> getParkingById(UUID id);
    void createParking(String name);
}
