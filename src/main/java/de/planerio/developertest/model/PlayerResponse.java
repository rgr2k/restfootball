package de.planerio.developertest.model;

public class PlayerResponse {

    private Long id;
    private String name;
    private PlayerPosition position;
    private Integer shirtNumber;
    private String team;
    private String league;

    public PlayerResponse(Long id, String name, PlayerPosition position, Integer shirtNumber, String team, String league) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.shirtNumber = shirtNumber;
        this.team = team;
        this.league = league;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public Integer getShirtNumber() {
        return shirtNumber;
    }

    public String getTeam() {
        return team;
    }

    public String getLeague() {
        return league;
    }
}
