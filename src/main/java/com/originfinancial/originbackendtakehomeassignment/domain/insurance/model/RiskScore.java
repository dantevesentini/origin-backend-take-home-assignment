package com.originfinancial.originbackendtakehomeassignment.domain.insurance.model;


public enum RiskScore {
    ECONOMIC("economic"),
    INELIGIBLE("ineligible"),
    REGULAR("regular"),
    RESPONSIBLE("responsible");

    private String description;

    RiskScore(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}