package com.originfinancial.originbackendtakehomeassignment.api.controller.model.request;

import javax.validation.constraints.PositiveOrZero;

public class VehicleRequest {

    @PositiveOrZero(message = "Year must be equal or greater than 0")
    private Integer year;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}
