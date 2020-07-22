package com.originfinancial.originbackendtakehomeassignment.api.controller;

import com.originfinancial.originbackendtakehomeassignment.api.controller.model.request.InsuranceRiskRequest;
import com.originfinancial.originbackendtakehomeassignment.api.controller.model.response.InsuranceRiskResponse;
import com.originfinancial.originbackendtakehomeassignment.domain.insurance.interactor.InsuranceRiskCalculatorUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class InsuranceRiskCalculatorController {

    @Autowired
    private InsuranceRiskCalculatorUseCase insuranceRiskCalculator;

    @PostMapping(value = "/insurances/risks", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<InsuranceRiskResponse> calculateInsuranceRisk(@Valid @RequestBody InsuranceRiskRequest request) {
        return ResponseEntity.ok(new InsuranceRiskResponse(insuranceRiskCalculator.execute(request)));
    }

}
