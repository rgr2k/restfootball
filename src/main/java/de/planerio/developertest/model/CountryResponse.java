package de.planerio.developertest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryResponse {

    private Long id;
    private String name;
    private String language;

    public CountryResponse() {
    }

    public CountryResponse(Long id, String name, String language) {
        this.id = id;
        this.name = name;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public Long getId() {
        return id;
    }
}
