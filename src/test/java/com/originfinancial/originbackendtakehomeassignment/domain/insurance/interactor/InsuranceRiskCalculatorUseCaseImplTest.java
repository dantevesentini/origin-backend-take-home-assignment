package com.originfinancial.originbackendtakehomeassignment.domain.insurance.interactor;

import com.originfinancial.originbackendtakehomeassignment.api.controller.model.request.InsuranceRiskRequest;
import com.originfinancial.originbackendtakehomeassignment.domain.insurance.model.InsuranceRisk;
import com.originfinancial.originbackendtakehomeassignment.domain.insurance.service.AutoInsuranceRiskService;
import com.originfinancial.originbackendtakehomeassignment.domain.insurance.service.DisabilityInsuranceRiskService;
import com.originfinancial.originbackendtakehomeassignment.domain.insurance.service.HomeInsuranceRiskService;
import com.originfinancial.originbackendtakehomeassignment.domain.insurance.service.LifeInsuranceRiskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class InsuranceRiskCalculatorUseCaseImplTest {

    @Mock
    AutoInsuranceRiskService autoInsuranceRiskService;
    @Mock
    LifeInsuranceRiskService lifeInsuranceRiskService;
    @Mock
    DisabilityInsuranceRiskService disabilityInsuranceRiskService;
    @Mock
    HomeInsuranceRiskService homeInsuranceRiskService;

    @InjectMocks
    InsuranceRiskCalculatorUseCaseImpl useCase = new InsuranceRiskCalculatorUseCaseImpl();

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnEconomicForAllInsurancePlans() {
        when(autoInsuranceRiskService.calculate(anyInt(), any())).thenReturn(0);
        when(lifeInsuranceRiskService.calculate(anyInt(), any())).thenReturn(0);
        when(disabilityInsuranceRiskService.calculate(anyInt(), any())).thenReturn(0);
        when(homeInsuranceRiskService.calculate(anyInt(), any())).thenReturn(0);

        InsuranceRiskRequest insuranceRiskRequest = new InsuranceRiskRequest();

        Boolean[] riskQuestions = {true, false, true};
        insuranceRiskRequest.setRiskQuestions(riskQuestions);
        InsuranceRisk insuranceRisk = useCase.execute(insuranceRiskRequest);
        Assertions.assertNotNull(insuranceRisk);
        Assertions.assertEquals("economic", insuranceRisk.getAuto());
        Assertions.assertEquals("economic", insuranceRisk.getLife());
        Assertions.assertEquals("economic", insuranceRisk.getDisability());
        Assertions.assertEquals("economic", insuranceRisk.getHome());
    }

    @Test
    void shouldReturnRegularForAllInsurancePlans() {
        when(autoInsuranceRiskService.calculate(anyInt(), any())).thenReturn(1);
        when(lifeInsuranceRiskService.calculate(anyInt(), any())).thenReturn(2);
        when(disabilityInsuranceRiskService.calculate(anyInt(), any())).thenReturn(1);
        when(homeInsuranceRiskService.calculate(anyInt(), any())).thenReturn(2);

        InsuranceRiskRequest insuranceRiskRequest = new InsuranceRiskRequest();

        Boolean[] riskQuestions = {true, false, true};
        insuranceRiskRequest.setRiskQuestions(riskQuestions);
        InsuranceRisk insuranceRisk = useCase.execute(insuranceRiskRequest);
        Assertions.assertNotNull(insuranceRisk);
        Assertions.assertEquals("regular", insuranceRisk.getAuto());
        Assertions.assertEquals("regular", insuranceRisk.getLife());
        Assertions.assertEquals("regular", insuranceRisk.getDisability());
        Assertions.assertEquals("regular", insuranceRisk.getHome());
    }

    @Test
    void shouldReturnResponsibleForAllInsurancePlans() {
        when(autoInsuranceRiskService.calculate(anyInt(), any())).thenReturn(3);
        when(lifeInsuranceRiskService.calculate(anyInt(), any())).thenReturn(4);
        when(disabilityInsuranceRiskService.calculate(anyInt(), any())).thenReturn(5);
        when(homeInsuranceRiskService.calculate(anyInt(), any())).thenReturn(6);

        InsuranceRiskRequest insuranceRiskRequest = new InsuranceRiskRequest();

        Boolean[] riskQuestions = {true, false, true};
        insuranceRiskRequest.setRiskQuestions(riskQuestions);
        InsuranceRisk insuranceRisk = useCase.execute(insuranceRiskRequest);
        Assertions.assertNotNull(insuranceRisk);
        Assertions.assertEquals("responsible", insuranceRisk.getAuto());
        Assertions.assertEquals("responsible", insuranceRisk.getLife());
        Assertions.assertEquals("responsible", insuranceRisk.getDisability());
        Assertions.assertEquals("responsible", insuranceRisk.getHome());
    }

    @Test
    void shouldReturnIneligibleForAllInsurancePlans() {
        when(autoInsuranceRiskService.calculate(anyInt(), any())).thenReturn(null);
        when(lifeInsuranceRiskService.calculate(anyInt(), any())).thenReturn(null);
        when(disabilityInsuranceRiskService.calculate(anyInt(), any())).thenReturn(null);
        when(homeInsuranceRiskService.calculate(anyInt(), any())).thenReturn(null);

        InsuranceRiskRequest insuranceRiskRequest = new InsuranceRiskRequest();

        Boolean[] riskQuestions = {true, false, true};
        insuranceRiskRequest.setRiskQuestions(riskQuestions);
        InsuranceRisk insuranceRisk = useCase.execute(insuranceRiskRequest);
        Assertions.assertNotNull(insuranceRisk);
        Assertions.assertEquals("ineligible", insuranceRisk.getAuto());
        Assertions.assertEquals("ineligible", insuranceRisk.getLife());
        Assertions.assertEquals("ineligible", insuranceRisk.getDisability());
        Assertions.assertEquals("ineligible", insuranceRisk.getHome());
    }

}