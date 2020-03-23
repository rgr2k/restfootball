package de.planerio.developertest.service;

import com.google.common.base.Strings;
import de.planerio.developertest.exception.PlayerNotFoundException;
import de.planerio.developertest.exception.BadRequestException;
import de.planerio.developertest.exception.TeamNotFoundException;
import de.planerio.developertest.model.*;
import de.planerio.developertest.repository.PlayerRepository;
import de.planerio.developertest.repository.TeamRepository;
import de.planerio.developertest.transformer.PlayerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.temporal.ValueRange;
import java.util.List;
import java.util.stream.Collectors;

import static de.planerio.developertest.exception.Constants.TEAM_NOT_FOUND;
import static de.planerio.developertest.exception.Constants.MORE_THAN_25_PLAYERS_PER_IN_THE_TEAM;
import static de.planerio.developertest.exception.Constants.PLAYER_NOT_FOUND;
import static de.planerio.developertest.exception.Constants.JERSEY_NUMBER_MUST_BE_1_99_RANGE;
import static de.planerio.developertest.exception.Constants.JERSEY_NUMBER_ALREADY_EXISTS;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public PlayerResponse save(PlayerRequest playerRequest){

        validateJerseyNumber(playerRequest);

        final Team team =
                teamRepository.findById(playerRequest.getTeamId())
                        .orElseThrow(() -> new TeamNotFoundException(TEAM_NOT_FOUND));

        if(team.getPlayers().size() > 24){
            throw new BadRequestException(MORE_THAN_25_PLAYERS_PER_IN_THE_TEAM);
        }

        Player player = PlayerTransformer.toEntity(playerRequest, team);
        return PlayerTransformer.toResponse(playerRepository.save(player));
    }

    public PlayerResponse findById(long playerId){
       final Player player =
               playerRepository.findById(playerId)
                       .orElseThrow(() -> new PlayerNotFoundException(PLAYER_NOT_FOUND));
       return PlayerTransformer.toResponse(player);
    }

    public List<PlayerResponse> findAll(List<PlayerPosition> positions, String sortBy, OrderBy orderBy){
        if(positions.size() == 0 && Strings.isNullOrEmpty(sortBy) && orderBy == null){
            return this.findAll();
        }

        final List<Player> players =
                playerRepository.findPlayerByPositionsInSort(positions, sortBy, orderBy);

        if (players.isEmpty()){
            throw new PlayerNotFoundException(PLAYER_NOT_FOUND);
        }

        return players.stream().map(PlayerTransformer::toResponse).collect(Collectors.toList());
    }

    public List<PlayerResponse> findAll(){
        List<PlayerResponse> players =
                playerRepository.findAll().stream()
                        .map(PlayerTransformer::toResponse).collect(Collectors.toList());
        if(players.isEmpty()){
            throw new PlayerNotFoundException(PLAYER_NOT_FOUND);
        }
        return players;
    }

    public void delete(long playerId){
        try {
            playerRepository.deleteById(playerId);
        }catch (EmptyResultDataAccessException ex){
            throw new PlayerNotFoundException(PLAYER_NOT_FOUND);
        }
    }

    public void update(PlayerUpdateRequest playerUpdateRequest, long playerId){
        Player player =
                playerRepository.findById(playerId)
                        .orElseThrow(() -> new PlayerNotFoundException(PLAYER_NOT_FOUND));
        validate(player, playerUpdateRequest);
        playerRepository.save(player);
    }

    private void validateJerseyNumber(PlayerRequest playerRequest) {
        if(!ValueRange.of(1, 99).isValidIntValue(playerRequest.getShirtNumber())){
            throw new BadRequestException(JERSEY_NUMBER_MUST_BE_1_99_RANGE);
        }

        if(playerRepository.existsByShirtNumber(playerRequest.getShirtNumber())){
            throw new BadRequestException(JERSEY_NUMBER_ALREADY_EXISTS);
        }
    }

    private void validate(Player player, PlayerUpdateRequest playerUpdateRequest){
        if(!Strings.isNullOrEmpty(playerUpdateRequest.getName())){
            player.setName(playerUpdateRequest.getName());
        }

        if(playerUpdateRequest.getPosition() != null){
            player.setPosition(playerUpdateRequest.getPosition());
        }

        if(playerUpdateRequest.getShirtNumber() != 0){
            player.setShirtNumber(playerUpdateRequest.getShirtNumber());
        }
    }
}
