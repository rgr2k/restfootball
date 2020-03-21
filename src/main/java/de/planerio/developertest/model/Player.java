package de.planerio.developertest.model;

import javax.persistence.*;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Team team;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PlayerPosition position;

    @Column(nullable = false)
    private int shirtNumber;

    public Player() {
    }

    public Player(String name, Team team, PlayerPosition position, int shirtNumber) {
        this.name = name;
        this.team = team;
        this.position = position;
        this.shirtNumber = shirtNumber;
    }

    public Long getId() {
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }
}
