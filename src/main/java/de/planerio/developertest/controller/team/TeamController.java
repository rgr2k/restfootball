package de.planerio.developertest.controller.team;

import de.planerio.developertest.model.*;
import de.planerio.developertest.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController implements TeamResource{

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public List<TeamResponse> getTeams() {
        return teamService.findAll();
    }

    @Override
    public TeamResponse getTeam(long teamId) {
        return teamService.findById(teamId);
    }

    @Override
    public TeamResponse createTeam(TeamRequest teamCreate) {
        return teamService.save(teamCreate);
    }

    @Override
    public void deleteCountry(long teamId) {
        teamService.delete(teamId);
    }

    @Override
    public void updateTeam(TeamUpdateRequest teamUpdateRequest, long teamId) {
        teamService.update(teamUpdateRequest, teamId);
    }
}
