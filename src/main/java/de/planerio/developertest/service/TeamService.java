package de.planerio.developertest.service;

import com.google.common.base.Strings;
import de.planerio.developertest.exception.LeagueNotFoundException;
import de.planerio.developertest.exception.ResourceNotAcceptableException;
import de.planerio.developertest.exception.TeamNotFoundException;
import de.planerio.developertest.model.*;
import de.planerio.developertest.repository.LeagueRepository;
import de.planerio.developertest.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository, LeagueRepository leagueRepository) {
        this.teamRepository = teamRepository;
        this.leagueRepository = leagueRepository;
    }

    public Team save(TeamCreate teamCreate){
        final Optional<League> league = leagueRepository.findById(teamCreate.getLeagueId());
        if(league.isEmpty()){
            throw new LeagueNotFoundException("League not found");
        }

        if(leagueRepository.countLeagueById(teamCreate.getLeagueId()) > 19){
            throw new ResourceNotAcceptableException("There cannot be more than 20 teams per league");
        }

        Team team = new Team();
        team.setName(teamCreate.getName());
        team.setLeague(league.get());
        Team savedTeam = teamRepository.save(team);
        return savedTeam;
    }

    public Optional<Team> findById(long teamId){
        return teamRepository.findById(teamId);
    }

    public List<TeamResponse> findAll(){
        return StreamSupport.stream(teamRepository.findAll().spliterator(), false)
                .map(this::buildTeam)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public void delete(long teamId){
        teamRepository.deleteById(teamId);
    }

    public void update(Team team, TeamUpdate teamUpdate){
        buildUpdatedTeam(team, teamUpdate);
        teamRepository.save(team);
    }

    public TeamResponse find(long teamId){
        return this.findById(teamId).flatMap(this::buildTeam).orElseThrow(() -> new TeamNotFoundException("Team not found"));
    }

    private Optional<TeamResponse> buildTeam(Team team) {
        TeamResponse teamResponse = new TeamResponse();
        teamResponse.setId(team.getId());
        teamResponse.setName(team.getName());

        LeagueResponse leagueResponse = new LeagueResponse();
        leagueResponse.setId(team.getLeague().getId());
        leagueResponse.setName(team.getLeague().getName());

        CountryResponse countryResponse = new CountryResponse();
        countryResponse.setName(team.getLeague().getCountry().getName());
        countryResponse.setLanguage(team.getLeague().getCountry().getLanguage());

        leagueResponse.setCountry(countryResponse);
        teamResponse.setLeague(leagueResponse);
        return Optional.of(teamResponse);
    }

    private void buildUpdatedTeam(Team team, TeamUpdate teamUpdate) {
        if(!Strings.isNullOrEmpty(teamUpdate.getName())){
            team.setName(teamUpdate.getName());
        }
    }
}
