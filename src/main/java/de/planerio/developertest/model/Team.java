package de.planerio.developertest.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private League league;

    @OneToMany(
            mappedBy = "team",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Player> players;

    public Team(String name) {
        this.name = name;
    }

    public Team() {
    }

    public Team(String name, League league, List<Player> players) {
        this.name = name;
        this.league = league;
        this.players = players;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("league", league)
                .add("players", players)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return Objects.equal(getId(), team.getId()) &&
                Objects.equal(getName(), team.getName()) &&
                Objects.equal(getLeague(), team.getLeague()) &&
                Objects.equal(getPlayers(), team.getPlayers());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName(), getLeague(), getPlayers());
    }
}
