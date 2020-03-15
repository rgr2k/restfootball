package de.planerio.developertest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Language is required")
    private String language;

    public CountryRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language.toLowerCase();
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
