package de.planerio.developertest.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "position", nullable = false)
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

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("team", team)
                .add("position", position)
                .add("shirtNumber", shirtNumber)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return getShirtNumber() == player.getShirtNumber() &&
                Objects.equal(getId(), player.getId()) &&
                Objects.equal(getName(), player.getName()) &&
                Objects.equal(getTeam(), player.getTeam()) &&
                getPosition() == player.getPosition();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName(), getTeam(), getPosition(), getShirtNumber());
    }
}
