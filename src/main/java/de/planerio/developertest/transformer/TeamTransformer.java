package de.planerio.developertest.transformer;

import de.planerio.developertest.model.Team;
import de.planerio.developertest.model.TeamRequest;
import de.planerio.developertest.model.TeamResponse;

import java.util.Collections;

public class TeamTransformer {

    public static TeamResponse toResponse(Team team){
        return new TeamResponse(
                team.getId(),
                team.getName(),
                LeagueTransformer.toResponse(team.getLeague()),
                PlayerTransformer.toResponse(team.getPlayers() == null ? Collections.emptyList() : team.getPlayers())
        );
    }

    public static Team toEntity(TeamRequest teamRequest){
        return new Team(teamRequest.getName());
    }
}
