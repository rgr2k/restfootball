package de.planerio.developertest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeagueUpdateRequest {

    private String name;

    @NotNull
    private CountryRequest country;

    public LeagueUpdateRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryRequest getCountry() {
        return country;
    }

    public void setCountry(CountryRequest country) {
        this.country = country;
    }
}
