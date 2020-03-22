package de.planerio.developertest.controller.league;

import de.planerio.developertest.model.LeagueRequest;
import de.planerio.developertest.model.LeagueResponse;
import de.planerio.developertest.model.LeagueUpdateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "leagues")
@RequestMapping(value = "/v1/leagues")
public interface LeagueResource {

    @ApiOperation(value = "Get leagues")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<LeagueResponse> getLeagues(@RequestParam(value = "language", required = false) String language);

    @ApiOperation(value = "Get league by id")
    @GetMapping(value = "/{leagueId}", produces = MediaType.APPLICATION_JSON_VALUE)
    LeagueResponse getLeague(@ApiParam(value = "ID of the league that needs to be found", required=true, example = "123") @PathVariable long leagueId);

    @ApiOperation(value = "Create league")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    LeagueResponse createLeague(@ApiParam(value = "Created league object" , required=true) @Valid @RequestBody LeagueRequest leagueRequest);

    @ApiOperation(value = "Delete league")
    @DeleteMapping("/{leagueId}")
    void deleteLeague(@ApiParam(value = "ID of the league that needs to be deleted", required=true, example = "123") @PathVariable long leagueId);

    @ApiOperation(value = "Update league")
    @PutMapping(value = "/{leagueId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateLeague(@ApiParam(value = "Updated league object" ,required=true) @Valid @RequestBody LeagueUpdateRequest leagueUpdate, @ApiParam(value = "ID of the league that needs to be updated", required=true, example = "123") @PathVariable long leagueId);
}
