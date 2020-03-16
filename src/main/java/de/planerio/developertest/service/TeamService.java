package de.planerio.developertest.service;

import de.planerio.developertest.exception.LeagueNotFoundException;
import de.planerio.developertest.model.*;
import de.planerio.developertest.repository.LeagueRepository;
import de.planerio.developertest.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Team team = new Team();
        team.setName(teamCreate.getName());
        team.setLeague(league.get());
        Team savedTeam = teamRepository.save(team);
        return savedTeam;
    }

    public Optional<Team> find(long teamId){
        return teamRepository.findById(teamId);
    }

    public Optional<Iterable<Team>> findAll(){
        final Iterable<Team> teams = teamRepository.findAll();
        return teams.iterator().hasNext() ? Optional.of(teams) : Optional.empty();
    }

    public void delete(long teamId){
        teamRepository.deleteById(teamId);
    }

    public void update(Team team, TeamUpdate teamUpdate){
        teamRepository.save(team);
    }
}
