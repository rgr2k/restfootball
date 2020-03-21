package de.planerio.developertest.transformer;

import de.planerio.developertest.model.Player;
import de.planerio.developertest.model.PlayerRequest;
import de.planerio.developertest.model.PlayerResponse;
import de.planerio.developertest.model.Team;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerTransformer {

    public static PlayerResponse toResponse(Player player){
        return new PlayerResponse(
                player.getId(),
                player.getName(),
                player.getPosition(),
                player.getShirtNumber(),
                player.getTeam().getName(),
                player.getTeam().getLeague().getName());
    }

    public static List<PlayerResponse> toResponse(List<Player> players){
        return players.stream().map(PlayerTransformer::toResponse).collect(Collectors.toList());
    }

    public static Player toEntity(PlayerRequest playerRequest, Team team){
        return new Player(playerRequest.getName(), team, playerRequest.getPosition(), playerRequest.getShirtNumber());
    }
}
