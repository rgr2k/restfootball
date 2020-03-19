package de.planerio.developertest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeagueRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Country is required")
    private CountryRequest country;

    public LeagueRequest() {
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
