package de.planerio.developertest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamResponse {

    private long id;
    private String name;
    private LeagueResponse league;

    public TeamResponse() {
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

    public LeagueResponse getLeague() {
        return league;
    }

    public void setLeague(LeagueResponse league) {
        this.league = league;
    }
}
