package com.originfinancial.originbackendtakehomeassignment.domain.insurance.service;

import com.originfinancial.originbackendtakehomeassignment.api.controller.model.request.HouseRequest;
import com.originfinancial.originbackendtakehomeassignment.api.controller.model.request.InsuranceRiskRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class HomeInsuranceRiskServiceTest {

    @Test
    void shouldReturnNull_WhenHouseEqualsNull() {
        InsuranceRiskService insuranceRiskService = new HomeInsuranceRiskService();
        InsuranceRiskRequest insuranceRiskRequest = new InsuranceRiskRequest();
        insuranceRiskRequest.setHouse(null);
        Integer score = insuranceRiskService.calculate(0, insuranceRiskRequest);
        Assertions.assertNull(score);
    }

    @Test
    void shouldReturnNull_WhenHouseOwnershipStatusEqualsNull() {
        InsuranceRiskService insuranceRiskService = new HomeInsuranceRiskService();
        InsuranceRiskRequest insuranceRiskRequest = new InsuranceRiskRequest();
        insuranceRiskRequest.setHouse(new HouseRequest());
        Integer score = insuranceRiskService.calculate(0, insuranceRiskRequest);
        Assertions.assertNull(score);
    }

    /**
     * Starts with 3 risk points.
     * User is under 30 years (remove two points),
     * Income is 999999.00 (remove one point),
     * ownership status equals mortgaged (add one point),
     * Should return 1.
     */
    @Test
    void shouldReturnOneRiskPoint() {
        InsuranceRiskService insuranceRiskService = new HomeInsuranceRiskService();
        InsuranceRiskRequest insuranceRiskRequest = new InsuranceRiskRequest();

        HouseRequest houseRequest = new HouseRequest();
        houseRequest.setOwnershipStatus("mortgaged");
        insuranceRiskRequest.setHouse(houseRequest);

        insuranceRiskRequest.setAge(25);
        insuranceRiskRequest.setIncome(BigDecimal.valueOf(999999));
        Assertions.assertEquals(1, insuranceRiskService.calculate(3, insuranceRiskRequest));
    }


    /**
     * Starts with 3 risk points.
     * User is under 30 years (remove two points),
     * Income is 999999.00 (remove one point),
     * ownership status equals owned (dont remove points),
     * Should return 0.
     */
    @Test
    void shouldReturnZeroRiskPoints() {
        InsuranceRiskService insuranceRiskService = new HomeInsuranceRiskService();
        InsuranceRiskRequest insuranceRiskRequest = new InsuranceRiskRequest();

        HouseRequest houseRequest = new HouseRequest();
        houseRequest.setOwnershipStatus("owned");
        insuranceRiskRequest.setHouse(houseRequest);

        insuranceRiskRequest.setAge(25);
        insuranceRiskRequest.setIncome(BigDecimal.valueOf(999999));
        Assertions.assertEquals(0, insuranceRiskService.calculate(3, insuranceRiskRequest));
    }
}