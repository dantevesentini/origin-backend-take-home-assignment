package com.originfinancial.originbackendtakehomeassignment.domain.insurance.model;

public class InsuranceRisk {

    private String auto;
    private String disability;
    private String home;
    private String life;

    public static class Builder {
        private String auto;
        private String disability;
        private String home;
        private String life;

        public Builder auto(String auto) {
            this.auto = auto;
            return this;
        }

        public Builder disability(String disability) {
            this.disability = disability;
            return this;
        }

        public Builder home(String home) {
            this.home = home;
            return this;
        }

        public Builder life(String life) {
            this.life = life;
            return this;
        }

        public InsuranceRisk build() {
            return new InsuranceRisk(this);
        }
    }

    private InsuranceRisk(Builder builder) {
        this.auto = builder.auto;
        this.disability = builder.disability;
        this.home = builder.home;
        this.life = builder.life;
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
