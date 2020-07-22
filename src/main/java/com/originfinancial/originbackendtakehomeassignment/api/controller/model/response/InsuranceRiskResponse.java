package com.originfinancial.originbackendtakehomeassignment.api.controller.model.response;

import com.originfinancial.originbackendtakehomeassignment.domain.insurance.model.InsuranceRisk;

import java.io.Serializable;

public class InsuranceRiskResponse implements Serializable {

    private final String auto;
    private final String disability;
    private final String home;
    private final String life;

    public InsuranceRiskResponse(InsuranceRisk insuranceRisk) {
        this.auto = insuranceRisk.getAuto();
        this.disability = insuranceRisk.getDisability();
        this.home = insuranceRisk.getHome();
        this.life = insuranceRisk.getLife();
    }

    public String getAuto() {
        return auto;
    }

    public String getDisability() {
        return disability;
    }

    public String getHome() {
        return home;
    }

    public String getLife() {
        return life;
    }

}
