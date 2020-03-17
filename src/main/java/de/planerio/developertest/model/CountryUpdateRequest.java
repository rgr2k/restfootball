package de.planerio.developertest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryUpdateRequest {

    private String name;
    private String language;

    public CountryUpdateRequest() {
    }

    public String getName() {
        return name;
    }
    public String getLanguage() {
        return language.toLowerCase();
    }
}
