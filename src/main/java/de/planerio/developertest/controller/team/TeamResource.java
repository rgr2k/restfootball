package de.planerio.developertest.controller.team;

import de.planerio.developertest.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "teams")
@RequestMapping(value = "/v1/teams")
public interface TeamResource {

    @ApiOperation(value = "Get teams")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<TeamResponse> retrieveAllTeam();

    @ApiOperation(value = "Get team by id")
    @GetMapping(value = "/{teamId}", produces = MediaType.APPLICATION_JSON_VALUE)
    TeamResponse retrieveTeam(@ApiParam(value = "ID of the team that needs to be found", required=true, example = "1") @PathVariable long teamId);

    @ApiOperation(value = "Create team")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    TeamResponse createTeam(@ApiParam(value = "Created team object" , required=true) @Valid @RequestBody TeamRequest teamCreate);

    @ApiOperation(value = "Delete team")
    @DeleteMapping("/{teamId}")
    void deleteCountry(@ApiParam(value = "ID of the team that needs to be deleted", required=true, example = "1") @PathVariable long teamId);

    @ApiOperation(value = "Update team")
    @PutMapping(value = "/{teamId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateTeam(@ApiParam(value = "Updated team object" ,required=true) @RequestBody TeamUpdateRequest teamUpdate, @ApiParam(value = "ID of the team that needs to be updated", required=true, example = "1") @PathVariable long teamId);
}
