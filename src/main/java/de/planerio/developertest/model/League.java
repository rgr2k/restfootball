package de.planerio.developertest.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.List;

@Entity
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Country country;

    @OneToMany(targetEntity = Team.class)
    private List<Team> teams;

    public League() {
    }

    public League(String name, Country country) {
        this.name = name;
        this.country = country;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("country", country)
                .add("teams", teams)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof League)) return false;
        League league = (League) o;
        return Objects.equal(getId(), league.getId()) &&
                Objects.equal(getName(), league.getName()) &&
                Objects.equal(getCountry(), league.getCountry()) &&
                Objects.equal(getTeams(), league.getTeams());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName(), getCountry(), getTeams());
    }
}
