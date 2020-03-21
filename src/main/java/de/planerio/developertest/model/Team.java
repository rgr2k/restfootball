package de.planerio.developertest.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private League league;

    @OneToMany(targetEntity = Player.class)
    private List<Player> players;

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    public Team(long id, String name, League league) {
        this.id = id;
        this.name = name;
        this.league = league;
    }

    public Team(long id, String name, League league, List<Player> players) {
        this.id = id;
        this.name = name;
        this.league = league;
        this.players = players;
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

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
