package com.vedverma.spring.security.parking.services.impl;

import com.vedverma.spring.security.parking.Parking;
import com.vedverma.spring.security.parking.services.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultParkingService implements ParkingService {

    private static final String LIST_ALL_PARKING = "http://localhost:8080/listAllParkings";

    private static final String GET_PARKING = "http://localhost:8080/findParkingById?id=%s";

    private static final String DELETE_PARKING = "http://localhost:8080/removeParkingById?id=%s";

    private static final String RENAME_PARKING = "http://localhost:8080/renameParking?id=%s&name=%s";

    private static final String CREATE_PARKING = "http://localhost:8080/createParking?name=%s&width=%s&height=%s";

    private static final Logger log = LoggerFactory.getLogger(DefaultParkingService.class);

    @Autowired
    private RestOperations restTemplate;

    @Override
    public List<Parking> getAllParking() {
        URI uri = URI.create(LIST_ALL_PARKING);

        List<Parking> parkingList = new ArrayList<>();
        try {
            parkingList = restTemplate.getForObject(uri, List.class);
        } catch (Exception ignored) {
            log.error("Exception", ignored);
        }
        return parkingList;
    }

    @Override
    public Optional<Parking> getParkingById(String id) {
        URI uri = URI.create(String.format(
                GET_PARKING,
                id
        ));

        try {
            Parking parking = restTemplate.getForObject(uri, Parking.class);
            return Optional.ofNullable(parking);
        } catch (Exception ignored) {
            log.error("Exception", ignored);
        }
        return Optional.empty();
    }

    @Override
    public void createParking(String name, Integer width, Integer height) {
        URI uri = URI.create(String.format(
                CREATE_PARKING,
                name,
                width,
                height
        ));

        try {
            restTemplate.getForObject(uri, Parking.class);
        } catch (Exception ignored) {
            log.error("Exception", ignored);
        }
    }

    @Override
    public void deleteParking(String id) {
        URI uri = URI.create(String.format(
                DELETE_PARKING,
                id
        ));

        try {
            restTemplate.getForObject(uri, Parking.class);
        } catch (Exception ignored) {
            log.error("Exception", ignored);
        }
    }

    @Override
    public void renameParking(String id, String name) {
        URI uri = URI.create(String.format(
                RENAME_PARKING,
                id,
                name
        ));

        try {
            restTemplate.getForObject(uri, Parking.class);
        } catch (Exception ignored) {
            log.error("Exception", ignored);
        }
    }
}
