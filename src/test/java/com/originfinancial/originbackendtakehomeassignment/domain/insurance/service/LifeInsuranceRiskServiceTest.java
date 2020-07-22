package com.originfinancial.originbackendtakehomeassignment.domain.insurance.service;

import com.originfinancial.originbackendtakehomeassignment.api.controller.model.request.InsuranceRiskRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class LifeInsuranceRiskServiceTest {

    @Test
    void shouldReturnNull_WhenOverSixtyYears() {
        InsuranceRiskService insuranceRiskService = new LifeInsuranceRiskService();
        InsuranceRiskRequest insuranceRiskRequest = new InsuranceRiskRequest();
        insuranceRiskRequest.setAge(61);
        Integer score = insuranceRiskService.calculate(0, insuranceRiskRequest);
        Assertions.assertNull(score);
    }

    /**
     * Starts with 3 risk points.
     * User is under 30 years (remove two points),
     * Income is 999999.00 (remove one point),
     * has 2 Dependents (add one point),
     * marrital status equals married (add one point),
     * Should return 2.
     */
    @Test
    void shouldReturnTwoRiskPoints() {
        InsuranceRiskService insuranceRiskService = new LifeInsuranceRiskService();
        InsuranceRiskRequest insuranceRiskRequest = new InsuranceRiskRequest();
        insuranceRiskRequest.setAge(25);
        insuranceRiskRequest.setIncome(BigDecimal.valueOf(999999));
        insuranceRiskRequest.setDependents(2);
        insuranceRiskRequest.setMaritalStatus("married");
        Assertions.assertEquals(2, insuranceRiskService.calculate(3, insuranceRiskRequest));
    }

    /**
     * Starts with 3 risk points.
     * User is over 30 years and under 60 (remove one points),
     * Income is 0.00 (dont remove points),
     * has 2 Dependents (add one point),
     * marrital status equals single (dont remove points),
     * Should return 3.
     */
    @Test
    void shouldReturnThreeRiskPoints() {
        InsuranceRiskService insuranceRiskService = new LifeInsuranceRiskService();
        InsuranceRiskRequest insuranceRiskRequest = new InsuranceRiskRequest();
        insuranceRiskRequest.setAge(35);
        insuranceRiskRequest.setIncome(BigDecimal.valueOf(0));
        insuranceRiskRequest.setDependents(2);
        insuranceRiskRequest.setMaritalStatus("single");
        Assertions.assertEquals(3, insuranceRiskService.calculate(3, insuranceRiskRequest));
    }

}