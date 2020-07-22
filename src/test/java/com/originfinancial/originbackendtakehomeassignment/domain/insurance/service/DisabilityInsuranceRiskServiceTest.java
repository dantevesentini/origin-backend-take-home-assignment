package com.originfinancial.originbackendtakehomeassignment.domain.insurance.service;

import com.originfinancial.originbackendtakehomeassignment.api.controller.model.request.HouseRequest;
import com.originfinancial.originbackendtakehomeassignment.api.controller.model.request.InsuranceRiskRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class DisabilityInsuranceRiskServiceTest {

    @Test
    void shouldReturnNull_WhenOverSixtyYears() {
        InsuranceRiskService insuranceRiskService = new DisabilityInsuranceRiskService();
        InsuranceRiskRequest insuranceRiskRequest = new InsuranceRiskRequest();
        insuranceRiskRequest.setIncome(BigDecimal.valueOf(200000));
        insuranceRiskRequest.setAge(61);
        Integer score = insuranceRiskService.calculate(0, insuranceRiskRequest);
        Assertions.assertNull(score);
    }

    @Test
    void shouldReturnNull_WhenIncomeEqualsZero() {
        InsuranceRiskService insuranceRiskService = new DisabilityInsuranceRiskService();
        InsuranceRiskRequest insuranceRiskRequest = new InsuranceRiskRequest();
        insuranceRiskRequest.setIncome(BigDecimal.ZERO);
        Integer score = insuranceRiskService.calculate(0, insuranceRiskRequest);
        Assertions.assertNull(score);
    }


    /**
     * Starts with 3 risk points.
     * User is under 30 years (remove two points),
     * Income is 999999.00 (remove one point),
     * has 2 Dependents (add one point),
     * ownership status equals mortgaged (add one point),
     * marrital status equals married (remove one point),
     * Should return 1.
     */
    @Test
    void shouldReturnOneRiskPoint() {
        InsuranceRiskService insuranceRiskService = new DisabilityInsuranceRiskService();
        InsuranceRiskRequest insuranceRiskRequest = new InsuranceRiskRequest();
        insuranceRiskRequest.setAge(25);
        HouseRequest houseRequest = new HouseRequest();
        houseRequest.setOwnershipStatus("mortgaged");
        insuranceRiskRequest.setHouse(houseRequest);
        insuranceRiskRequest.setIncome(BigDecimal.valueOf(999999));
        insuranceRiskRequest.setDependents(2);
        insuranceRiskRequest.setMaritalStatus("married");
        Assertions.assertEquals(1, insuranceRiskService.calculate(3, insuranceRiskRequest));
    }

    /**
     * Starts with 3 risk points.
     * User is 40 years (remove one points),
     * Income is 999999.00 (remove one point),
     * has 2 Dependents (add one point),
     * marrital status equals single (dont remove points),
     * Should return 2.
     */
    @Test
    void shouldReturnOneZeroPoints() {
        InsuranceRiskService insuranceRiskService = new DisabilityInsuranceRiskService();
        InsuranceRiskRequest insuranceRiskRequest = new InsuranceRiskRequest();
        insuranceRiskRequest.setAge(35);
        insuranceRiskRequest.setHouse(null);
        insuranceRiskRequest.setIncome(BigDecimal.valueOf(999999));
        insuranceRiskRequest.setDependents(2);
        insuranceRiskRequest.setMaritalStatus("single");
        Assertions.assertEquals(2, insuranceRiskService.calculate(3, insuranceRiskRequest));
    }
}