package de.planerio.developertest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryUpdate {

    private String name;
    private String language;

    public CountryUpdate() {
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
