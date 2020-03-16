package de.planerio.developertest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamUpdate {

    private String name;
    private LeagueUpdate league;

    public TeamUpdate() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LeagueUpdate getLeague() {
        return league;
    }

    public void setLeague(LeagueUpdate league) {
        this.league = league;
    }
}
