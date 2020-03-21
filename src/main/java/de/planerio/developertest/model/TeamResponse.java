package de.planerio.developertest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamResponse {

    private Long id;
    private String name;
    private LeagueResponse league;
    private List<PlayerResponse> players;

    public TeamResponse(Long id, String name, LeagueResponse league, List<PlayerResponse> players) {
        this.id = id;
        this.name = name;
        this.league = league;
        this.players = players;
    }

    public Long getId() {
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
