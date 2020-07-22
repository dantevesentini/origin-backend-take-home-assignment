package com.originfinancial.originbackendtakehomeassignment.domain.insurance.service;

import com.originfinancial.originbackendtakehomeassignment.api.controller.model.request.InsuranceRiskRequest;
import org.springframework.stereotype.Service;

@Service
public class LifeInsuranceRiskService extends InsuranceRiskService {

    @Override
    public Integer calculate(Integer riskBaseValue, InsuranceRiskRequest request) {
        Integer score;
        Integer age = request.getAge();

        if (isMoreThanSixty(age)) return null;

        score = calculateRiskByAge(age, riskBaseValue);
        score = calculateRiskByIncome(request.getIncome(), score);
        score = calculateRiskByDependents(request.getDependents(), score);
        score = calculateRiskByMaritalStatus(request.getMaritalStatus(), score);

        return score;
    }

}
