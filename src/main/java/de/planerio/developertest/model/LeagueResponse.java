package de.planerio.developertest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeagueResponse {

    private long id;
    private String name;
    private CountryResponse country;

    public LeagueResponse(long id, String name, CountryResponse country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public LeagueResponse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryResponse getCountry() {
        return country;
    }

    public void setCountry(CountryResponse country) {
        this.country = country;
    }
}
