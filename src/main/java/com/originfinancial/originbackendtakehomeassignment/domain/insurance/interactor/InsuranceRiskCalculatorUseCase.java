package com.originfinancial.originbackendtakehomeassignment.domain.insurance.interactor;

import com.originfinancial.originbackendtakehomeassignment.api.controller.model.request.InsuranceRiskRequest;
import com.originfinancial.originbackendtakehomeassignment.domain.insurance.model.InsuranceRisk;

public interface InsuranceRiskCalculatorUseCase {

    InsuranceRisk execute(InsuranceRiskRequest request);

}