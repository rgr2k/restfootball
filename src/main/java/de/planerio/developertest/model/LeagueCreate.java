package de.planerio.developertest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeagueCreate {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Country is required")
    private CountryCreate country;

    public LeagueCreate() {
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
