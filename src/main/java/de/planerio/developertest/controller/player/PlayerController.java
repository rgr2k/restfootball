package de.planerio.developertest.controller.player;

import de.planerio.developertest.model.PlayerPosition;
import de.planerio.developertest.model.PlayerRequest;
import de.planerio.developertest.model.PlayerResponse;
import de.planerio.developertest.model.PlayerUpdateRequest;
import de.planerio.developertest.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PlayerController implements PlayerResource {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @Override
    public List<PlayerResponse> getPlayers(List<PlayerPosition> positions, String sortBy, String orderBy) {
        return playerService.findAll(positions, sortBy, orderBy);
    }

    @Override
    public PlayerResponse getPlayer(long playerId) {
        return playerService.findById(playerId);
    }

    @Override
    public PlayerResponse createPlayer(@Valid PlayerRequest playerRequest) {
        return playerService.save(playerRequest);
    }

    @Override
    public void deletePlayer(long playerId) {
        playerService.delete(playerId);
    }

    @Override
    public void updatePlayer(@Valid PlayerUpdateRequest playerUpdate, long playerId) {
        playerService.update(playerUpdate, playerId);
    }
}
