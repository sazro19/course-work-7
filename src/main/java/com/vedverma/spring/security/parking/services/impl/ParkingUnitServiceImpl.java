package com.vedverma.spring.security.parking.services.impl;

import com.vedverma.spring.security.parking.ParkingUnit;
import com.vedverma.spring.security.parking.services.ParkingUnitService;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingUnitServiceImpl implements ParkingUnitService {

    private static final String LIST_ALL_PARKING_UNIT = "http://localhost:8080/listParkingUnits?parkingId=%s";

    private static final String SAVE_PARKING_UNIT = "http://localhost:8080/saveParkingUnit";

    @Autowired
    private RestOperations restTemplate;

    private static final Logger log = LoggerFactory.getLogger(DefaultParkingService.class);

    @Override
    public List<ParkingUnit> getAllParkingUnits(String parkingId) {
        URI uri = URI.create(String.format(
                LIST_ALL_PARKING_UNIT,
                parkingId
        ));

        List<ParkingUnit> parkingUnitList = new ArrayList<>();
        try {
            parkingUnitList = restTemplate.getForObject(uri, List.class);
        } catch (Exception ignored) {
            log.error("Exception", ignored);
        }
        return parkingUnitList;
    }

    @Override
    public void createParkingUnit(ParkingUnit parkingUnit) {
        URI uri = URI.create(SAVE_PARKING_UNIT);

        try {
            restTemplate.postForObject(uri, parkingUnit, String.class);
        } catch (Exception ignored) {
            log.error("Exception", ignored);
        }
    }
}
