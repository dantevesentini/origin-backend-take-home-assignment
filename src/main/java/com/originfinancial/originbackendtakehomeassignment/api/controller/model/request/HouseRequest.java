package com.originfinancial.originbackendtakehomeassignment.api.controller.model.request;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.validation.constraints.Pattern;

public class HouseRequest {

    @JsonAlias("ownership_status")
    @Pattern(regexp = "(?i)^(owned|mortgaged)$", message = "Ownership status must be single or married")
    private String ownershipStatus;

    public String getOwnershipStatus() {
        return ownershipStatus;
    }

    public void setOwnershipStatus(String ownershipStatus) {
        this.ownershipStatus = ownershipStatus;
    }

}
