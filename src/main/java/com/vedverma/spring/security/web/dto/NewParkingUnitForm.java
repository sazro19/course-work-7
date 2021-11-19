package com.vedverma.spring.security.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class NewParkingUnitForm {

    @NotEmpty(message = "Parking id cannot be empty")
    private String parkingId;

    @NotNull(message = "Floor cannot be empty")
    @Min(value = 1, message = "Floor must be positive")
    private Integer floor;

    @NotNull(message = "Section cannot be empty")
    @Min(value = 1, message = "Section must be positive")
    private Integer section;

    @NotNull(message = "Cell cannot be empty")
    @Min(value = 1, message = "Cell must be positive")
    private Integer cell;

    @NotEmpty(message = "Type cannot be empty")
    private String type;

    @NotEmpty(message = "Status cannot be empty")
    private String status;

    public String getParkingId() {
        return parkingId;
    }

    public void setParkingId(String parkingId) {
        this.parkingId = parkingId;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public Integer getCell() {
        return cell;
    }

    public void setCell(Integer cell) {
        this.cell = cell;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
