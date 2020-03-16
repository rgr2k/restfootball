package de.planerio.developertest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamCreate {

    @NotBlank
    private String name;
    private Long leagueId;

    public TeamCreate() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }
}
