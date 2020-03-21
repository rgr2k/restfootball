package de.planerio.developertest.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class PlayerRequest {


    private String name;

    private PlayerPosition position;

    private Integer shirtNumber;

    private Long teamId;

    public PlayerRequest() {
    }

    public PlayerRequest(String name, PlayerPosition position, Integer shirtNumber) {
        this.name = name;
        this.position = position;
        this.shirtNumber = shirtNumber;
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

    public Long getTeamId() {
        return teamId;
    }
}
