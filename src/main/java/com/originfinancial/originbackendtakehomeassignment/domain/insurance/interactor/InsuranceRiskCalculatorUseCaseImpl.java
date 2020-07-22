package com.originfinancial.originbackendtakehomeassignment.domain.insurance.interactor;

import com.originfinancial.originbackendtakehomeassignment.api.controller.model.request.InsuranceRiskRequest;
import com.originfinancial.originbackendtakehomeassignment.domain.insurance.model.InsuranceRisk;
import com.originfinancial.originbackendtakehomeassignment.domain.insurance.model.RiskScore;
import com.originfinancial.originbackendtakehomeassignment.domain.insurance.service.AutoInsuranceRiskService;
import com.originfinancial.originbackendtakehomeassignment.domain.insurance.service.DisabilityInsuranceRiskService;
import com.originfinancial.originbackendtakehomeassignment.domain.insurance.service.HomeInsuranceRiskService;
import com.originfinancial.originbackendtakehomeassignment.domain.insurance.service.LifeInsuranceRiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsuranceRiskCalculatorUseCaseImpl implements InsuranceRiskCalculatorUseCase {

    @Autowired
    private HomeInsuranceRiskService homeInsuranceRiskCalculator;

    @Autowired
    private LifeInsuranceRiskService lifeInsuranceRiskCalculator;

    @Autowired
    private AutoInsuranceRiskService autoInsuranceRiskCalculator;

    @Autowired
    private DisabilityInsuranceRiskService disabilityInsuranceRiskCalculator;

    public InsuranceRisk execute(InsuranceRiskRequest request) {

        int riskBaseValue = 0;

        for (Boolean riskQuestion : request.getRiskQuestions()) {
            riskBaseValue += riskQuestion ? 1 : 0;
        }

        final var homeInsuranceRiskScore = homeInsuranceRiskCalculator.calculate(riskBaseValue, request);
        final var lifeInsuranceRiskScore = lifeInsuranceRiskCalculator.calculate(riskBaseValue, request);
        final var autoInsuranceRiskScore = autoInsuranceRiskCalculator.calculate(riskBaseValue, request);
        final var disabilityInsuranceRiskScore = disabilityInsuranceRiskCalculator.calculate(riskBaseValue, request);

        return new InsuranceRisk.Builder()
                .home(mapScoreToReadeableDescription(homeInsuranceRiskScore))
                .life(mapScoreToReadeableDescription(lifeInsuranceRiskScore))
                .auto(mapScoreToReadeableDescription(autoInsuranceRiskScore))
                .disability(mapScoreToReadeableDescription(disabilityInsuranceRiskScore))
                .build();
    }

    private String mapScoreToReadeableDescription(Integer score) {
        if (score == null) return RiskScore.INELIGIBLE.getDescription();
        if (score <= 0) return RiskScore.ECONOMIC.getDescription();
        if (score < 3) return RiskScore.REGULAR.getDescription();
        return RiskScore.RESPONSIBLE.getDescription();
    }

}
