package de.planerio.developertest.controller.league;

import de.planerio.developertest.exception.LeagueNotFoundException;
import de.planerio.developertest.model.League;
import de.planerio.developertest.model.LeagueCreate;
import de.planerio.developertest.model.LeagueUpdate;
import de.planerio.developertest.service.LeagueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
public class LeagueController implements LeagueResource {

    private static final Logger log = LoggerFactory.getLogger(LeagueController.class);

    private final LeagueService leagueService;

    @Autowired
    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @Override
    public Iterable<League> retrieveAllLeagues() {
        return leagueService.findAll().orElseThrow(() -> new LeagueNotFoundException("There are no leagues found"));
    }

    @Override
    public League retrieveLeague(long leagueId) {
        return leagueService.find(leagueId).orElseThrow(() -> new LeagueNotFoundException("There is no league found"));
    }

    @Override
    public League createLeague(@Valid LeagueCreate leagueRequest) {
        return leagueService.save(leagueRequest);
    }

    @Override
    public void deleteLeague(long leagueId) {
        leagueService.find(leagueId).orElseThrow(() -> new LeagueNotFoundException("There is no league found"));
        leagueService.delete(leagueId);
    }

    @Override
    public void updateLeague(LeagueUpdate leagueUpdate, long leagueId) {
        League league = leagueService.find(leagueId).orElseThrow(() -> new LeagueNotFoundException("There is no league found"));
        leagueService.update(league, leagueUpdate);
    }
}
