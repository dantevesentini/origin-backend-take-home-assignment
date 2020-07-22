package com.originfinancial.originbackendtakehomeassignment.api.controller.model.request;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class InsuranceRiskRequest {

    @PositiveOrZero(message = "Age must be equal or greater than 0")
    private Integer age;

    @PositiveOrZero(message = "Dependents must be equal or greater than 0")
    private Integer dependents;

    @Valid
    private HouseRequest house;

    @PositiveOrZero(message = "Income must be equal or greater than 0")
    private BigDecimal income;

    @NotEmpty
    @JsonAlias("marital_status")
    @Pattern(regexp = "(?i)^(single|married)$", message = "Marital status must be single or married")
    private String maritalStatus;

    @NotNull
    @JsonAlias("risk_questions")
    private Boolean[] riskQuestions;

    @Valid
    private VehicleRequest vehicle;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getDependents() {
        return dependents;
    }

    public void setDependents(Integer dependents) {
        this.dependents = dependents;
    }

    public HouseRequest getHouse() {
        return house;
    }

    public void setHouse(HouseRequest house) {
        this.house = house;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Boolean[] getRiskQuestions() {
        return riskQuestions;
    }

    public void setRiskQuestions(Boolean[] riskQuestions) {
        this.riskQuestions = riskQuestions;
    }

    public VehicleRequest getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleRequest vehicle) {
        this.vehicle = vehicle;
    }

}
