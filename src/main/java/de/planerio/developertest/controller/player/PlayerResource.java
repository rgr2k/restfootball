package de.planerio.developertest.controller.player;

import de.planerio.developertest.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "players")
@RequestMapping(value = "/v1/players")
public interface PlayerResource {

    @ApiOperation(value = "Get players")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<PlayerResponse> getPlayers(@RequestParam(value = "positions", required = false) List<PlayerPosition> positions,
                                    @RequestParam(value = "sort_by", required = false) String sortBy,
                                    @RequestParam(value = "order_by", required = false) OrderBy orderBy);

    @ApiOperation(value = "Get league by id")
    @GetMapping(value = "/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    PlayerResponse getPlayer(@ApiParam(value = "ID of the league that needs to be found", required=true, example = "123") @PathVariable long playerId);

    @ApiOperation(value = "Create player")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    PlayerResponse createPlayer(@ApiParam(value = "Created player object" , required=true) @Valid @RequestBody PlayerRequest playerRequest);

    @ApiOperation(value = "Delete player")
    @DeleteMapping("/{playerId}")
    void deletePlayer(@ApiParam(value = "ID of the player that needs to be deleted", required=true, example = "123") @PathVariable long playerId);

    @ApiOperation(value = "Update player")
    @PutMapping(value = "/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void updatePlayer(@ApiParam(value = "Updated player object" ,required=true) @Valid @RequestBody PlayerUpdateRequest playerUpdate, @ApiParam(value = "ID of the league that needs to be updated", required=true, example = "123") @PathVariable long playerId);
}
