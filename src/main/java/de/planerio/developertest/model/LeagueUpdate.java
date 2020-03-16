package de.planerio.developertest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeagueUpdate {

    private String name;

    @NotNull
    private CountryCreate country;

    public LeagueUpdate() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryCreate getCountry() {
        return country;
    }

    public void setCountry(CountryCreate country) {
        this.country = country;
    }
}
