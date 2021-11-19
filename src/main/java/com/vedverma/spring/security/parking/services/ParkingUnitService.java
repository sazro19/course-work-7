package com.vedverma.spring.security.parking.services;

import com.vedverma.spring.security.parking.ParkingUnit;

import java.util.List;

public interface ParkingUnitService {
    List<ParkingUnit> getAllParkingUnits(String parkingId);
    void createParkingUnit(ParkingUnit parkingUnit);
}
