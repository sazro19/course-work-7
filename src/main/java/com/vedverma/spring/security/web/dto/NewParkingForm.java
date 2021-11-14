package com.vedverma.spring.security.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class NewParkingForm {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Width cannot be empty")
    @Min(value = 1, message = "Width must be positive")
    private Integer width;

    @NotNull(message = "Height cannot be empty")
    @Min(value = 1, message = "Height must be positive")
    private Integer height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
