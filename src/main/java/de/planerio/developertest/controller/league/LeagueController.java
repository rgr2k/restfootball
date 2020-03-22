package de.planerio.developertest.controller.league;

import de.planerio.developertest.model.LeagueRequest;
import de.planerio.developertest.model.LeagueResponse;
import de.planerio.developertest.model.LeagueUpdateRequest;
import de.planerio.developertest.service.LeagueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
public class LeagueController implements LeagueResource {

    private static final Logger log = LoggerFactory.getLogger(LeagueController.class);

    private final LeagueService leagueService;

    @Autowired
    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @Override
    public List<LeagueResponse> getLeagues(String language) {
        return leagueService.findAll(language);
    }

    @Override
    public LeagueResponse getLeague(long leagueId) {
        return leagueService.find(leagueId);
    }

    @Override
    public LeagueResponse createLeague(@Valid LeagueRequest leagueRequest) {
        return leagueService.save(leagueRequest);
    }

    @Override
    public void deleteLeague(long leagueId) {
        leagueService.delete(leagueId);
    }

    @Override
    public void updateLeague(LeagueUpdateRequest leagueUpdate, long leagueId) {
        leagueService.update(leagueUpdate, leagueId);
    }
}
