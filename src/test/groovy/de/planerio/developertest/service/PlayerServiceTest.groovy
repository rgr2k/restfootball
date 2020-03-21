package de.planerio.developertest.service

import de.planerio.developertest.UnitTest
import de.planerio.developertest.exception.PlayerNotFoundException
import de.planerio.developertest.exception.ResourceNotAcceptableException
import de.planerio.developertest.model.Country
import de.planerio.developertest.model.League
import de.planerio.developertest.model.Player
import de.planerio.developertest.model.PlayerPosition
import de.planerio.developertest.model.PlayerRequest
import de.planerio.developertest.model.PlayerUpdateRequest
import de.planerio.developertest.model.Team
import de.planerio.developertest.repository.PlayerRepository
import de.planerio.developertest.repository.TeamRepository
import org.junit.experimental.categories.Category
import org.springframework.dao.EmptyResultDataAccessException
import spock.lang.Specification

@Category(UnitTest.class)
class PlayerServiceTest extends Specification{

    def playerRepository = Mock(PlayerRepository)
    def teamRepository = Mock(TeamRepository)
    def playerService = new PlayerService(playerRepository, teamRepository)
    def playerRequest = new PlayerRequest(name: "Player 1", position: PlayerPosition.CAM, shirtNumber: 12)
    def country = new Country(name: "German", language: "de")
    def league = new League(name: "My league", country: country)
    def team = new Team(name: "My Team", league: league)
    def player = new Player(id: 1, name: "Player 1", team: team ,position: PlayerPosition.CAM, shirtNumber: 12)

    def "save - there cannot be more than 25 players in a team - exception is thrown"(){
        given:
        team.players = createPlayers(25)

        when:
        playerService.save(playerRequest)

        then:
        thrown(ResourceNotAcceptableException)

        and:
        1 * teamRepository.findById(_) >> Optional.of(team)
    }

    def "save"(){
        given:
        team.players = createPlayers(10)

        when:
        def playerResponse = playerService.save(playerRequest)

        then:
        playerResponse.name == playerRequest.name

        and:
        1 * teamRepository.findById(_) >> Optional.of(team)

        and:
        1 * playerRepository.save(_) >> player
    }

    def "findById"(){
        when:
        def playerResponse = playerService.findById(1)

        then:
        playerResponse.name == "Player 1"
        playerResponse.position == PlayerPosition.CAM
        playerResponse.shirtNumber == 12

        and:
        1 * playerRepository.findById(_) >> Optional.of(player)
    }

    def "findById - player not found - exception is thrown"(){
        when:
        playerService.findById(1)

        then:
        thrown(PlayerNotFoundException)

        and:
        1 * playerRepository.findById(_) >> Optional.empty()
    }

    def "findAll"(){
        when:
        def playersResponse = playerService.findAll()

        then:
        playersResponse.size() > 4

        and:
        1 * playerRepository.findAll() >> createPlayers(5)
    }

    def "findAll - player not found - exception is thrown"(){
        when:
        playerService.findAll()

        then:
        thrown(PlayerNotFoundException)

        and:
        1 * playerRepository.findAll() >> []
    }

    def "delete"(){
        when:
        playerService.delete(123)

        then:
        noExceptionThrown()
    }

    def "delete - player not found - exception is thrown"(){
        when:
        playerService.delete(1)

        then:
        thrown(PlayerNotFoundException)

        and:
        1 * playerRepository.deleteById(_) >> {throw new EmptyResultDataAccessException("",1)}
    }

    def "update - player not found - exception is thrown"(){
        given:
        def playerUpdateRequest = new PlayerUpdateRequest(name: "Player 1", position: PlayerPosition.CAM, shirtNumber: 12)

        when:
        playerService.update(playerUpdateRequest, 1)

        then:
        thrown(PlayerNotFoundException)

        and:
        1 * playerRepository.findById(_) >> Optional.empty()
    }

    def "update"(){
        given:
        def playerUpdateRequest = new PlayerUpdateRequest(name: "Player 1", position: PlayerPosition.CAM, shirtNumber: 12)

        when:
        playerService.update(playerUpdateRequest, 1)

        then:
        noExceptionThrown()

        and:
        1 * playerRepository.findById(_) >> Optional.of(player)

        and:
        1 * playerRepository.save(_)
    }

    def createPlayers(int numberOfPlayers){
        int count = 1
        def players = []
        while(count<=numberOfPlayers) {
            def team = new Team(name: "My Team", league: league, players: [])
            players.add(new Player(name: "Player " + count, team: team, position: PlayerPosition.CAM, shirtNumber: count))
            count++
        }
        return players
    }
}
