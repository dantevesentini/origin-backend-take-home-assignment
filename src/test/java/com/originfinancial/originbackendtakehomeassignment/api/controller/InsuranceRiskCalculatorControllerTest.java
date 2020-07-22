package com.originfinancial.originbackendtakehomeassignment.api.controller;

import com.originfinancial.originbackendtakehomeassignment.domain.insurance.interactor.InsuranceRiskCalculatorUseCase;
import com.originfinancial.originbackendtakehomeassignment.domain.insurance.model.InsuranceRisk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class InsuranceRiskCalculatorControllerTest {

    private MockMvc mockMvc;

    @Mock
    InsuranceRiskCalculatorUseCase useCase;

    @InjectMocks
    InsuranceRiskCalculatorController controller = new InsuranceRiskCalculatorController();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void shouldReturnOk() throws Exception {

        InsuranceRisk response = new InsuranceRisk.Builder()
                .auto("economic")
                .life("regular")
                .disability("economic")
                .home("ineligible")
                .build();

        String request = "{\n" +
                "    \"age\": 60,\n" +
                "    \"dependents\": 1,\n" +
                "    \"house\": {\n" +
                "        \"ownership_status\": \"owned\"\n" +
                "    },\n" +
                "    \"income\": 2330.00,\n" +
                "    \"marital_status\": \"married\",\n" +
                "    \"risk_questions\": [\n" +
                "        1,\n" +
                "        1,\n" +
                "        1\n" +
                "    ],\n" +
                "    \"vehicle\": {\n" +
                "        \"year\": \"2016\"\n" +
                "    }\n" +
                "}";

        when(useCase.execute(any())).thenReturn(response);
        mockMvc.perform(post("/insurances/risks")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(request))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequestStatus() throws Exception {
        when(useCase.execute(any())).thenReturn(any());
        mockMvc.perform(post("/insurances/risks")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(""))
                .andExpect(status().is4xxClientError());
    }

}