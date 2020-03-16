package de.planerio.developertest.controller.team;

import de.planerio.developertest.exception.TeamNotFoundException;
import de.planerio.developertest.model.*;
import de.planerio.developertest.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
public class TeamController implements TeamResource{

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public Iterable<Team> retrieveAllTeam() {
        return teamService.findAll().orElseThrow(() -> new TeamNotFoundException("Teams not found"));
    }

    @Override
    public Team retrieveTeam(long teamId) {
        return teamService.find(teamId).orElseThrow(() -> new TeamNotFoundException("Team not found"));
    }

    @Override
    public Team createTeam(@Valid TeamCreate teamCreate) {
        return teamService.save(teamCreate);
    }

    @Override
    public void deleteCountry(long teamId) {
        teamService.find(teamId).orElseThrow(() -> new TeamNotFoundException("Team not found"));
        teamService.delete(teamId);
    }

    @Override
    public void updateTeam(TeamUpdate teamUpdate, long teamId) {
        Team team = teamService.find(teamId).orElseThrow(() -> new TeamNotFoundException("Team not found"));
        teamService.update(team, teamUpdate);
    }
}
