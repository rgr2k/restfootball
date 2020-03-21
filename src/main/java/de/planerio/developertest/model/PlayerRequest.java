package de.planerio.developertest.model;

import javax.persistence.Entity;

@Entity
public class PlayerRequest {

    private long id;
    private String name;
    private String position;
    private int shirtNumber;

    public PlayerRequest(long id, String name, String position, int shirtNumber) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.shirtNumber = shirtNumber;
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
}
