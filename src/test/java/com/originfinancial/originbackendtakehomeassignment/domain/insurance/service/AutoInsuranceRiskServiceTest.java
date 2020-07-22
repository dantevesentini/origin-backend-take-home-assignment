package com.originfinancial.originbackendtakehomeassignment.domain.insurance.service;

import com.originfinancial.originbackendtakehomeassignment.api.controller.model.request.InsuranceRiskRequest;
import com.originfinancial.originbackendtakehomeassignment.api.controller.model.request.VehicleRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class AutoInsuranceRiskServiceTest {

    @Test
    void shouldReturnNull_WhenVehicleEqualsNull() {
        InsuranceRiskService insuranceRiskService = new AutoInsuranceRiskService();
        InsuranceRiskRequest insuranceRiskRequest = new InsuranceRiskRequest();
        insuranceRiskRequest.setVehicle(null);
        Integer score = insuranceRiskService.calculate(0, insuranceRiskRequest);
        Assertions.assertNull(score);
    }

    @Test
    void shouldReturnNull_WhenVehicleYearEqualsNull() {
        InsuranceRiskService insuranceRiskService = new AutoInsuranceRiskService();
        InsuranceRiskRequest insuranceRiskRequest = new InsuranceRiskRequest();
        insuranceRiskRequest.setVehicle(new VehicleRequest());
        Integer score = insuranceRiskService.calculate(0, insuranceRiskRequest);
        Assertions.assertNull(score);
    }

    /**
     * Starts with 3 risk points.
     * User is under 30 years (remove two points),
     * Income is 999999.00 (remove one point),
     * Vehicle year is 1999 (do not remove points).
     * Should return 0.
     */
    @Test
    void shouldReturnZeroRiskPoints() {
        InsuranceRiskService insuranceRiskService = new AutoInsuranceRiskService();
        InsuranceRiskRequest insuranceRiskRequest = new InsuranceRiskRequest();

        VehicleRequest vehicleRequestMoreThanTwentyYears = new VehicleRequest();
        vehicleRequestMoreThanTwentyYears.setYear(1999);
        insuranceRiskRequest.setVehicle(vehicleRequestMoreThanTwentyYears);

        insuranceRiskRequest.setAge(25);
        insuranceRiskRequest.setIncome(BigDecimal.valueOf(999999));
        Assertions.assertEquals(0, insuranceRiskService.calculate(3, insuranceRiskRequest));
    }

    /**
     * Starts with 3 risk points.
     * User is under 30 years (remove two points),
     * Income is 999999.00 (remove one point),
     * Vehicle year is 2020 (do not remove points).
     * Should return 1.
     */
    @Test
    void shouldReturnOneRiskPoint() {
        InsuranceRiskService insuranceRiskService = new AutoInsuranceRiskService();
        InsuranceRiskRequest insuranceRiskRequest = new InsuranceRiskRequest();

        VehicleRequest vehicleRequestMoreThanTwentyYears = new VehicleRequest();
        vehicleRequestMoreThanTwentyYears.setYear(2020);
        insuranceRiskRequest.setVehicle(vehicleRequestMoreThanTwentyYears);

        insuranceRiskRequest.setAge(25);
        insuranceRiskRequest.setIncome(BigDecimal.valueOf(999999));
        Assertions.assertEquals(1, insuranceRiskService.calculate(3, insuranceRiskRequest));
    }


}