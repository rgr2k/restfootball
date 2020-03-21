package de.planerio.developertest.model;

import javax.persistence.*;

@Entity
public class PlayerResponse {

    private long id;
    private String name;
    private String position;
    private int shirtNumber;
    private Team team;

    public PlayerResponse(long id, String name, String position, int shirtNumber, Team team) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.shirtNumber = shirtNumber;
        this.team = team;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public Team getTeam() {
        return team;
    }
}
