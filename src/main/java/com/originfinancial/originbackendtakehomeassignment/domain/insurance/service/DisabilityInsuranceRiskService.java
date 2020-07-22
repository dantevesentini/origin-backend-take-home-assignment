package com.originfinancial.originbackendtakehomeassignment.domain.insurance.service;

import com.originfinancial.originbackendtakehomeassignment.api.controller.model.request.HouseRequest;
import com.originfinancial.originbackendtakehomeassignment.api.controller.model.request.InsuranceRiskRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Service
public class DisabilityInsuranceRiskService extends InsuranceRiskService {

    @Override
    public Integer calculate(Integer riskBaseValue, InsuranceRiskRequest request) {
        Integer score;
        Integer age = request.getAge();
        BigDecimal income = request.getIncome();

        if (income.floatValue() <= 0 || super.isMoreThanSixty(age)) return null;

        score = calculateRiskByAge(age, riskBaseValue);
        score = calculateRiskByIncome(income, score);

        HouseRequest house = request.getHouse();
        if (house != null && !StringUtils.isEmpty(house.getOwnershipStatus())) {
            score = calculateRiskByMortgagedOwnershipStatus(house.getOwnershipStatus(), score);
        }

        score = calculateRiskByDependents(request.getDependents(), score);
        score = this.calculateRiskByMaritalStatus(request.getMaritalStatus(), score);

        return score;
    }

    @Override
    public Integer calculateRiskByMaritalStatus(String maritalStatus, Integer score) {
        return maritalStatus.equals("married") ? score - 1 : score;
    }

}
