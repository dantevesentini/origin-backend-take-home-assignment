package com.originfinancial.originbackendtakehomeassignment.domain.insurance.service;

import com.originfinancial.originbackendtakehomeassignment.api.controller.model.request.HouseRequest;
import com.originfinancial.originbackendtakehomeassignment.api.controller.model.request.InsuranceRiskRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HomeInsuranceRiskService extends InsuranceRiskService {

    @Override
    public Integer calculate(Integer riskBaseValue, InsuranceRiskRequest request) {
        Integer score;
        HouseRequest house = request.getHouse();

        if (house == null || StringUtils.isEmpty(house.getOwnershipStatus())) return null;

        score = calculateRiskByAge(request.getAge(), riskBaseValue);
        score = calculateRiskByIncome(request.getIncome(), score);
        score = calculateRiskByMortgagedOwnershipStatus(house.getOwnershipStatus(), score);
        return score;
    }
}
