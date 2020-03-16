package de.planerio.developertest.controller.league;

import de.planerio.developertest.model.League;
import de.planerio.developertest.model.LeagueCreate;
import de.planerio.developertest.model.LeagueUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "leagues")
@RequestMapping(value = "/v1/leagues")
public interface LeagueResource {

    @ApiOperation(value = "Get leagues")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Iterable<League> retrieveAllLeagues();

    @ApiOperation(value = "Get league by id")
    @GetMapping(value = "/{leagueId}", produces = MediaType.APPLICATION_JSON_VALUE)
    League retrieveLeague(@ApiParam(value = "ID of the league that needs to be found", required=true, example = "123") @PathVariable long leagueId);

    @ApiOperation(value = "Create league")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    League createLeague(@ApiParam(value = "Created league object" , required=true) @Valid @RequestBody LeagueCreate leagueRequest);

    @ApiOperation(value = "Delete league")
    @DeleteMapping("/{leagueId}")
    void deleteLeague(@ApiParam(value = "ID of the league that needs to be deleted", required=true, example = "123") @PathVariable long leagueId);

    @ApiOperation(value = "Update league")
    @PutMapping(value = "/{leagueId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateLeague(@ApiParam(value = "Updated league object" ,required=true) @Valid @RequestBody LeagueUpdate leagueUpdate, @ApiParam(value = "ID of the league that needs to be updated", required=true, example = "123") @PathVariable long leagueId);
}
