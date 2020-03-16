package de.planerio.developertest.controller.player;

import de.planerio.developertest.model.Player;
import de.planerio.developertest.model.PlayerCreate;
import de.planerio.developertest.model.PlayerUpdate;
import de.planerio.developertest.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PlayerController implements PlayerResource {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public Iterable<Player> retrieveAllPlayers() {
        return null;
    }

    @Override
    public Player retrievePlayer(long playerId) {
        return null;
    }

    @Override
    public Player createPlayer(@Valid PlayerCreate playerCreate) {
        return null;
    }

    @Override
    public void deletePlayer(long playerId) {

    }

    @Override
    public void updatePlayer(@Valid PlayerUpdate playerUpdate, long playerId) {

    }
}
