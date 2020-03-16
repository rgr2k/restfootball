package de.planerio.developertest.controller.team;

import de.planerio.developertest.exception.TeamNotFoundException;
import de.planerio.developertest.model.*;
import de.planerio.developertest.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TeamController implements TeamResource{

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public List<TeamResponse> retrieveAllTeam() {
        List<TeamResponse> teamsResponse = teamService.findAll();
        if(teamsResponse.isEmpty()){
            throw new TeamNotFoundException("Teams not found");
        }
        return teamsResponse;
    }

    @Override
    public TeamResponse retrieveTeam(long teamId) {
        return teamService.find(teamId);
    }

    @Override
    public Team createTeam(TeamCreate teamCreate) {
        return teamService.save(teamCreate);
    }

    @Override
    public void deleteCountry(long teamId) {
        teamService.findById(teamId).orElseThrow(() -> new TeamNotFoundException("Team not found"));
        teamService.delete(teamId);
    }

    @Override
    public void updateTeam(TeamUpdate teamUpdate, long teamId) {
        Team team = teamService.findById(teamId).orElseThrow(() -> new TeamNotFoundException("Team not found"));
        teamService.update(team, teamUpdate);
    }
}
