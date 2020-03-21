package de.planerio.developertest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerUpdateRequest {

    private String name;
    private PlayerPosition position;
    private Integer shirtNumber;

    public PlayerUpdateRequest() {
    }

    public PlayerUpdateRequest(String name, PlayerPosition position, Integer shirtNumber) {
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
}
