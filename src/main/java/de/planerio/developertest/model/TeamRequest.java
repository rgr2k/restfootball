package de.planerio.developertest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamRequest {

    @NotBlank
    private String name;

    @NotNull
    private Long leagueId;

    public TeamRequest() {
    }

    public TeamRequest(@NotBlank String name, @NotNull Long leagueId) {
        this.name = name;
        this.leagueId = leagueId;
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
