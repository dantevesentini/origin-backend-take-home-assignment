package com.originfinancial.originbackendtakehomeassignment.domain.insurance.service;

import com.originfinancial.originbackendtakehomeassignment.api.controller.model.request.InsuranceRiskRequest;

import java.math.BigDecimal;

public abstract class InsuranceRiskService {

    public abstract Integer calculate(Integer riskBaseValue, InsuranceRiskRequest request);

    public Integer calculateRiskByAge(Integer age, Integer score) {
        if (age < 30) return score - 2;
        if (age <= 40) return score - 1;
        return score;
    }

    public boolean isMoreThanSixty(Integer age) {
        return age > 60;
    }

    public Integer calculateRiskByIncome(BigDecimal income, Integer score) {
        return income.floatValue() > 200000.00 ? score - 1 : score;
    }

    public Integer calculateRiskByDependents(Integer dependents, Integer score) {
        return dependents > 0 ? score + 1 : score;
    }

    public Integer calculateRiskByMortgagedOwnershipStatus(String ownershipStatus, Integer score) {
        return ownershipStatus.equalsIgnoreCase("mortgaged") ? score + 1 : score;
    }

    public Integer calculateRiskByMaritalStatus(String maritalStatus, Integer score) {
        return maritalStatus.equals("married") ? score + 1 : score;
    }

}
