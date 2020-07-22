package com.originfinancial.originbackendtakehomeassignment.domain.insurance.service;

import com.originfinancial.originbackendtakehomeassignment.api.controller.model.request.InsuranceRiskRequest;
import com.originfinancial.originbackendtakehomeassignment.api.controller.model.request.VehicleRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Service
public class AutoInsuranceRiskService extends InsuranceRiskService {

    @Override
    public Integer calculate(Integer riskBaseValue, InsuranceRiskRequest request) {
        Integer score;

        VehicleRequest vehicle = request.getVehicle();

        if (vehicle == null || StringUtils.isEmpty(vehicle.getYear())) return null;

        score = calculateRiskByAge(request.getAge(), riskBaseValue);
        score = calculateRiskByIncome(request.getIncome(), score);
        score = this.calculateRiskByVehicleYear(vehicle.getYear(), score);
        return score;
    }

    private Integer calculateRiskByVehicleYear(Integer year, Integer score) {
        return LocalDate.now().getYear() - year <= 5 ? score + 1 : score;
    }

}
