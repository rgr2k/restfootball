package de.planerio.developertest.service;

import de.planerio.developertest.exception.LeagueNotFoundException;
import de.planerio.developertest.exception.ResourceNotAcceptableException;
import de.planerio.developertest.exception.TeamNotFoundException;
import de.planerio.developertest.model.*;
import de.planerio.developertest.repository.LeagueRepository;
import de.planerio.developertest.repository.TeamRepository;
import de.planerio.developertest.transformer.TeamTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static de.planerio.developertest.exception.Constants.*;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository, LeagueRepository leagueRepository) {
        this.teamRepository = teamRepository;
        this.leagueRepository = leagueRepository;
    }

    public TeamResponse save(TeamRequest teamRequest){
        final League league =
                leagueRepository.findById(teamRequest.getLeagueId())
                        .orElseThrow(() -> new LeagueNotFoundException(LEAGUE_NOT_FOUND));

        if(league.getTeams().size() > 19){
            throw new ResourceNotAcceptableException(MORE_THAN_20_TEAMS_PER_LEAGUE);
        }

        Team team = TeamTransformer.toEntity(teamRequest);
        team.setLeague(league);
        return TeamTransformer.toResponse(teamRepository.save(team));
    }

    public TeamResponse findById(long teamId){
        final Team team =
                teamRepository.findById(teamId)
                        .orElseThrow(() -> new TeamNotFoundException(TEAM_NOT_FOUND));

        return TeamTransformer.toResponse(team);
    }

    public List<TeamResponse> findAll(){
        final List<TeamResponse> teamResponses =
                StreamSupport.stream(teamRepository.findAll().spliterator(), false)
                        .map(TeamTransformer::toResponse)
                        .collect(Collectors.toList());

        if(teamResponses.isEmpty()){
            throw new TeamNotFoundException(TEAM_NOT_FOUND);
        }

        return teamResponses;
    }

    public void delete(long teamId){
        try {
            teamRepository.deleteById(teamId);
        }catch (EmptyResultDataAccessException ex){
            throw new TeamNotFoundException(TEAM_NOT_FOUND);
        }
    }

    public void update(TeamUpdateRequest teamUpdate, long teamId){
        Team team =
                teamRepository.findById(teamId)
                        .orElseThrow(() -> new TeamNotFoundException(TEAM_NOT_FOUND));
        team.setName(teamUpdate.getName());
        teamRepository.save(team);
    }
}
