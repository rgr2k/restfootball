package de.planerio.developertest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamResponse {

    private long id;
    private String name;
    private LeagueResponse league;
    private List<PlayerResponse> players;

    public TeamResponse() {
    }

    public TeamResponse(long id, String name, LeagueResponse league, List<PlayerResponse> players) {
        this.id = id;
        this.name = name;
        this.league = league;
        this.players = players;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LeagueResponse getLeague() {
        return league;
    }

    public List<PlayerResponse> getPlayers() {
        return players;
    }
}
